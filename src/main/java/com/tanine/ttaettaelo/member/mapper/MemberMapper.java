package com.tanine.ttaettaelo.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tanine.ttaettaelo.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	int insertMember(MemberDto member_info);
}
