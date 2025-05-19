package com.tanine.ttaettaelo.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.component.SessionManager;
import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.service.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	private final PasswordEncoder passwordEncoder;
	private final SessionManager sessionManager;
	
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "로그아웃 되었습니다.");
        }
        return "redirect:http://localhost:8082/login"; // login.html 또는 login.jsp로 리턴
    }
		
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO request, HttpServletResponse response) {		
	    String loginId = request.getLoginId();
	    String password = request.getPassword();
	    
	    LoginDTO member = loginService.login(loginId, password, response);
		
		if(member == null) { // 로그인 실패
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		return ResponseEntity.ok(member); // 로그인 성공
	}
	
//	@GetMapping("/session")
//	public ResponseEntity<?> getSession(HttpSession httpSession) {
//	    LoginDTO loginMember = (LoginDTO) httpSession.getAttribute("member");
//
//	    if (loginMember == null) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 상태가 아닙니다.");
//	    }
//
//	    return ResponseEntity.ok(loginMember);
//	}
	
	// 로그아웃
//	@GetMapping("/logout")
//	public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
////		session.invalidate(); // 설정된 세션 삭제
//		
//		HttpSession session = httpServletRequest.getSession(false);
//		if(session != null) {
//			session.invalidate();
//		}
//		
//		return ResponseEntity.ok("로그아웃 성공");
//	}
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		sessionManager.expire(request); // 세션 종료
		return ResponseEntity.ok().build(); // 로그아웃
	}
}
