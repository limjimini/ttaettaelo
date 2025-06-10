package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tanine.ttaettaelo.dto.LoginDTO;

/**
 * 회원 관련 데이터베이스 연동을 처리하는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface LoginMapper {
	
	LoginDTO loginMember(String loginId); // 로그인
}
