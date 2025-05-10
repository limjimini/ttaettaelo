package com.tanine.ttaettaelo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.dto.MemberDTO;
import com.tanine.ttaettaelo.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	
	// 로그인 아이디 중복 체크
	public boolean isDuplicatedLoginId(String loginId) {
		return memberMapper.countLoginId(loginId) > 0; // 1개라도 있으면 true
	}
	
	// 회원가입
	@Transactional
	public void saveMember(MemberDTO memberDto) {
		
		// 비밀번호 암호화
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		
		memberMapper.saveMember(memberDto); // 회원 저장
	}
	
	// 회원가입시 유효성 검사
	@Transactional(readOnly=true)
	public Map<String, String> validateHandling(Errors errors) {
		
		Map<String, String> validatorResult = new HashMap<>(); // 필드 오류를 받을 Map
		
		// 유효성 검사에 실패한 필드 목록 받기
		for(FieldError error : errors.getFieldErrors()) {
			String validKey = String.format("valid_%s", error.getField());
			validatorResult.put(validKey, error.getDefaultMessage());
		}
		return validatorResult;
	}
	
	public String getMemberNameById(Long memberId) {
        // member_id로 사용자 정보 가져오기
        MemberDTO member = memberMapper.getMemberById(memberId);
        return member != null ? member.getName() : "익명";  // 사용자 이름 리턴
    }
}
