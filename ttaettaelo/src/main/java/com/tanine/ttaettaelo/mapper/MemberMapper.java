package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tanine.ttaettaelo.dto.MemberDTO;

/**
 * 회원 관련 데이터베이스 연동을 처리하는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface MemberMapper {
	
	int countLoginId(String loginId); // 중복 아이디 체크
	
	void insertMember(MemberDTO memberDto); // 회원 등록
	
	MemberDTO getMemberById(Long memberId); // 회원 번호로 회원 정보 얻기
	
	String getLoginIdByNameAndEmail(@Param("name") String name, @Param("email") String email); // 이름과 이메일 주소로 로그인 아이디 찾기
	
	MemberDTO getMemberByLoginIdAndEmail(@Param("loginId") String loginId, @Param("email") String email); // 로그인 아이디와 이메일 주소로 회원 정보 얻기

	MemberDTO getMemberByLoginId(@Param("loginId") String loginId); // 로그인 아이디로 회원 정보 얻기
		
	int updateMember(MemberDTO memberDto); // 회원 정보 변경
	
	void updatePassword(MemberDTO memberDto); // 비밀번호 변경
	
	int deleteMemberById(@Param("memberId") Long memberId); // 회원 번호로 회원 삭제
}
