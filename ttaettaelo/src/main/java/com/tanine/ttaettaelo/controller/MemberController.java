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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.MemberDTO;
import com.tanine.ttaettaelo.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
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
}
