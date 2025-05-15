package com.tanine.ttaettaelo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.tanine.ttaettaelo.component.SessionManager;
import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.dto.MemberDTO;
import com.tanine.ttaettaelo.dto.MemberUpdatedDTO;
import com.tanine.ttaettaelo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final SessionManager sessionManager;
	
	// 로그인 중복 아이디 확인
	@GetMapping("/checkLoginId")
	public ResponseEntity<?> checkDuplicatedId(@RequestParam("loginId") String loginId) {
		boolean isDuplicated = memberService.isDuplicatedLoginId(loginId); // 중복 확인
		return ResponseEntity.ok(isDuplicated ? "duplicate" : "available"); // 중복이면 duplicate 반환
	}

	// 회원가입
	@PostMapping("/signUp")
	public ResponseEntity<?> saveMember(@Valid @RequestBody MemberDTO memberDto, BindingResult bindingResult) {
		
		if(!memberDto.isPasswordChecked()) { // 비밀번호와 비밀번호 확인이 일치하는지 체크
			bindingResult.rejectValue("passwordCheck", "비밀번호가 일치하지 않습니다.");
		}
		
		if(bindingResult.hasErrors()) { // 유효성 검사에 오류가 있으면
            Map<String, String> errorMessages = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> { // 오류 메세지 담기
                errorMessages.put(error.getField(), error.getDefaultMessage());
            });
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
		}
		
		try {
		    memberService.saveMember(memberDto); // 회원가 저장
		    return ResponseEntity.ok("회원가입 성공");
		} catch (Exception e) {
		    e.printStackTrace();
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("/findId")
	public ResponseEntity<?> findId(@RequestBody MemberDTO mebmerDto) {
	    String loginId = memberService.findLoginId(mebmerDto.getName(), mebmerDto.getEmail());
	    if (loginId == null) {
	        return ResponseEntity.ok(Map.of(
	                "success", false,
	                "message", "일치하는 사용자가 없습니다."
	            ));
	    }
	    
	    return ResponseEntity.ok(Map.of(
	            "success", true,
	            "loginId", loginId
	        ));
	}
	
    @PostMapping("/findPassword")
    public ResponseEntity<Map<String, Object>> findPassword(@RequestBody MemberDTO memberDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = memberService.sendTemporaryPassword(memberDto.getLoginId(), memberDto.getEmail());
            if (success) {
                response.put("success", true);
                response.put("message", "임시 비밀번호가 이메일로 전송되었습니다.");
            } else {
                response.put("success", false);
                response.put("message", "사용자를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            response.put("success", false);
            response.put("message", "서버에서 오류가 발생했습니다.");
        }
        return ResponseEntity.ok(response);
    }
	
//	@PostMapping("/mypage")
//	public ResponseEntity<?> getMypage(@RequestBody Map<String, Object> payload) {
//	    Object idObj = payload.get("memberId");
//
//	    if (idObj == null) {
//	        return ResponseEntity.badRequest().body("memberId가 누락되었습니다.");
//	    }
//
//	    try {
//	        Long memberId = Long.parseLong(idObj.toString());
//	        MemberDTO memberDto = memberService.getMemberById(memberId);
//
//	        if (memberDto == null) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 정보를 찾을 수 없습니다.");
//	        }
//
//	        return ResponseEntity.ok(memberDto);
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류: " + e.getMessage());
//	    }
//	}

	@PutMapping("/updateMember")
    public ResponseEntity<Map<String, Object>> updateMember(@RequestBody MemberDTO memberDto) {
		Map<String, Object> response = new HashMap<>();
		try {
        	MemberDTO updatedMember = memberService.updateMember(memberDto);
            response.put("success", true);
            response.put("user", updatedMember);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "정보 업데이트에 실패했습니다!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
		
	@PutMapping("/changePassword")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody Map<String, String> request) {
	    String loginId = request.get("loginId");
	    String newPassword = request.get("newPassword");
	    
		Map<String, Object> response = new HashMap<>();
		try {
			boolean success = memberService.changePassword(loginId, newPassword);
			if (success) {
                response.put("success", true);
                response.put("message", "비밀번호가 변경되었습니다.");
            } else {
                response.put("success", false);
                response.put("message", "비밀번호 변경에 실패하였습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "정보 업데이트에 실패했습니다!");
        }
		return ResponseEntity.ok(response);
    }
	
	@PostMapping("/deleteAccount")
	public ResponseEntity<Map<String, Object>> deleteAccount(@RequestBody MemberDTO memberDto) {
		boolean isDeleted = memberService.deleteAccount(memberDto.getMemberId());
		Map<String, Object> response = new HashMap<>();
		if(isDeleted) {
            response.put("success", true);
            response.put("message", "탈퇴 완료");
            return ResponseEntity.ok(response);
		} else {
			response.put("success", false);
            response.put("message", "탈퇴 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
