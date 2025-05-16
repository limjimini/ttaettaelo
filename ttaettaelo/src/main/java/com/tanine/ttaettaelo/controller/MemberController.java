package com.tanine.ttaettaelo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.MemberDTO;
import com.tanine.ttaettaelo.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	/**
	 * 로그인 중복 아이디 확인
	 * @param loginId 확인할 로그인 아이디
	 * @return 로그인 아이디 중복 여부
	 */
	@GetMapping("/checkLoginId")
	public ResponseEntity<?> checkDuplicatedId(@RequestParam("loginId") String loginId) {
		boolean isDuplicated = memberService.isDuplicatedLoginId(loginId); // 중복 확인
		return ResponseEntity.ok(isDuplicated ? "duplicate" : "available"); // 중복이면 duplicate 반환
	}

	/**
	 * 회원가입
	 * @param memberDto 가입할 회원 정보
	 * @param bindingResult 유효성 검사 결과
	 * @return 가입 성공 또는 실패 응답
	 */
	@PostMapping("/signUp")
	public ResponseEntity<?> saveMember(@Valid @RequestBody MemberDTO memberDto, BindingResult bindingResult) {	
	    // 중복 아이디 확인
		if (memberService.isDuplicatedLoginId(memberDto.getLoginId())) {
	        bindingResult.rejectValue("loginId", "이미 사용 중인 아이디입니다.");
	    }
		
		// 비밀번호와 비밀번호 확인이 일치하는지 확인
		if(!memberDto.isPasswordChecked()) {
			bindingResult.rejectValue("passwordCheck", "비밀번호가 일치하지 않습니다.");
		}
		
		// 유효성 검사에 오류가 있으면
		if(bindingResult.hasErrors()) {
            Map<String, String> errorMessages = new HashMap<>();
            
            bindingResult.getFieldErrors().forEach(error -> { // 오류 메세지 담기
                errorMessages.put(error.getField(), error.getDefaultMessage());
            });
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages); // 오류 메세지 반환
		}
		
		try {
		    memberService.saveMember(memberDto); // 회원 저장
		    return ResponseEntity.ok("회원가입 성공");
		} catch (Exception e) {
		    e.printStackTrace();
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * 로그인 아이디 찾기
	 * @param mebmerDto 회원 정보
	 * @return <성공 여부, 로그인 아이디>
	 */
	@PostMapping("/findId")
	public ResponseEntity<?> findLoginId(@RequestBody MemberDTO mebmerDto) {
	    String loginId = memberService.findLoginId(mebmerDto.getName(), mebmerDto.getEmail()); // 이름과 이메일 주소로 로그인 아이디 찾기
	    
	    if (loginId == null) { // 로그인 아이디 못 찾으면
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
	
	/**
	 * 비밀번호 찾기
	 * @param memberDto 회원 정보
	 * @return <성공 여부, 메세지>
	 */
    @PostMapping("/findPassword")
    public ResponseEntity<Map<String, Object>> findPassword(@RequestBody MemberDTO memberDto) {
        Map<String, Object> response = new HashMap<>();        
        try {
        	// 임시 비밀번호 메일 보내기
            boolean success = memberService.sendTemporaryPassword(memberDto.getLoginId(), memberDto.getEmail());
            
            if (success) { // 전송 성공
                response.put("success", true);
                response.put("message", "임시 비밀번호가 이메일로 전송되었습니다.");
            } else { // 전송 실패
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
	
    /**
     * 회원 정보 변경(이름, 이메일, 성별, 주소)
     * @param memberDto 변경할 회원 정보
     * @return <성공 여부, 변경된 회원 정보>
     */
	@PutMapping("/changeMember")
    public ResponseEntity<Map<String, Object>> changeMember(@RequestBody MemberDTO memberDto) {
		Map<String, Object> response = new HashMap<>();		
		try {
        	MemberDTO changedMember = memberService.changeMember(memberDto); // 회원 정보 변경       	
            response.put("success", true);
            response.put("user", changedMember);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "정보 업데이트에 실패했습니다!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
	/**
	 * 비밀번호 변경
	 * @param changeRequest 변경 요청
	 * @return <성공 여부, 메세지>
	 */
	@PutMapping("/changePassword")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody Map<String, String> changeRequest) {
	    String loginId = changeRequest.get("loginId"); // 로그인 아이디 얻기
	    String newPassword = changeRequest.get("newPassword"); // 새 비밀번호 얻기
	    
		Map<String, Object> response = new HashMap<>();		
		try {
			boolean success = memberService.changePassword(loginId, newPassword); // 비밀번호 변경			
			if (success) { // 변경 성공
                response.put("success", true);
                response.put("message", "비밀번호가 변경되었습니다.");
            } else { // 변경 실패
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
	
	/**
	 * 회원 탈퇴
	 * @param memberDto 회원 정보
	 * @return <성공 여부, 메세지>
	 */
	@PostMapping("/deleteAccount")
	public ResponseEntity<Map<String, Object>> deleteAccount(@RequestBody MemberDTO memberDto) {
		boolean isDeleted = memberService.deleteAccount(memberDto.getMemberId()); // 회원 탈
		
		Map<String, Object> response = new HashMap<>();
		if(isDeleted) { // 탈퇴 성공
            response.put("success", true);
            response.put("message", "탈퇴 완료");
            return ResponseEntity.ok(response);
		} else { // 탈퇴 실패
			response.put("success", false);
            response.put("message", "탈퇴 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
