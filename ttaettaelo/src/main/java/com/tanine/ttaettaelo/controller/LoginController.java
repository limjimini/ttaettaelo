package com.tanine.ttaettaelo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 로그인 관련 요청을 처리하는 컨트롤러 서비스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

	private final LoginService loginService;
			
    /**
     * 로그인(POST)
     * @param request 로그인 정보
     * @param response HTTP 응답 객체
     * @return 로그인 성공 시 로그인 정보 반환, 로그인 실패 시 오류 메시지 반환
     */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO request, HttpServletResponse response) {
	    String loginId = request.getLoginId();
	    String password = request.getPassword();
	    
	    LoginDTO member = loginService.login(loginId, password, response); // 로그인 처리
		
		if(member == null) { // 로그인 실패
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		return ResponseEntity.ok(member); // 로그인 성공
	}
		
	/**
	 * 로그아웃
	 * @param request HTTP 요청 객체
	 * @param response HTTP 응답 객체
	 * @return 로그아웃 성공 응답
	 */
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		return ResponseEntity.ok().build();
	}
}
