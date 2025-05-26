package com.tanine.ttaettaelo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.component.SessionManager;
import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.mapper.LoginMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginMapper loginMapper;
	private final PasswordEncoder passwordEncoder;
//	private final SessionManager sessionManager;

	// 로그인
	public LoginDTO login(String logindId, String password, HttpServletResponse response) {
		
		LoginDTO member = loginMapper.loginMember(logindId); // 회원 정보 조회
		
		if(member == null || !passwordEncoder.matches(password, member.getPassword())) {
			return null; // 로그인 실패
		}
		
		// 로그인 성공시 세션 생성
//		sessionManager.createSession(member, response);
		
		return member; // 로그인 성공
	}
}
