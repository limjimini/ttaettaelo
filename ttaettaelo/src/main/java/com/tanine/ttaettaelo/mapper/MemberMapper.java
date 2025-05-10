package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	int countLoginId(String loginId); // 중복아이디 체크
	
	void saveMember(MemberDTO memberDto); // 회원가입
	
	LoginDTO loginMember(String loginId); // 로그인
	
	MemberDTO getMemberById(Long memberId);
}
