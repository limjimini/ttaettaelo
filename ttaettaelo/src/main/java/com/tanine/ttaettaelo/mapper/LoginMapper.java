package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tanine.ttaettaelo.dto.LoginDTO;

@Mapper
public interface LoginMapper {
	
	LoginDTO loginMember(String loginId); // 로그인
}
