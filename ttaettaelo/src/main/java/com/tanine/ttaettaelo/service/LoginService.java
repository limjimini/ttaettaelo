package com.tanine.ttaettaelo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.component.SessionManager;
import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.mapper.LoginMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 로그인 관련 서비스를 처리하는 클래스
 * 로그인 기능을 제공한다.
 */
@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginMapper loginMapper;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 회원 로그인
	 * @param logindId 회원의 로그인 아이디
	 * @param password 회원의 비밀번호
	 * @param response HTTP 응답 객체
	 * @return 로그인 성공한 회원 정보 / 로그인 실패 시 null
	 */
	public LoginDTO login(String logindId, String password, HttpServletResponse response) {
		
		LoginDTO member = loginMapper.loginMember(logindId); // 회원 정보 조회
		
		if(member == null || !passwordEncoder.matches(password, member.getPassword())) {
			return null; // 로그인 실패
		}
				
		return member; // 로그인 성공
	}
}
