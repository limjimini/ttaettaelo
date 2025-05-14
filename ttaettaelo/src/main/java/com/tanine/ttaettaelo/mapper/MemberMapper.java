package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tanine.ttaettaelo.dto.LoginDTO;
import com.tanine.ttaettaelo.dto.MemberDTO;
import com.tanine.ttaettaelo.dto.MemberUpdatedDTO;

@Mapper
public interface MemberMapper {
	
	int countLoginId(String loginId); // 중복아이디 체크
	
	void saveMember(MemberDTO memberDto); // 회원가입
	
	LoginDTO loginMember(String loginId); // 로그인
	
	String getLoginIdByNameEmail(@Param("name") String name, @Param("email") String email); // 로그인 아이디 찾기
	
	MemberDTO getMemberById(Long memberId);
	
	int updateMember(MemberDTO memberDto);
	
	MemberDTO getMemberByLoginIdEmail(@Param("loginId") String loginId, @Param("email") String email);
	
	void updatePassword(MemberDTO memberDto);
	
	MemberDTO getMemberByLoginId(@Param("loginId") String loginId);
}
