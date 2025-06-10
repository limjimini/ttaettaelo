package com.tanine.ttaettaelo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tanine.ttaettaelo.dto.SupportAnswerDTO;
import com.tanine.ttaettaelo.dto.SupportDTO;

/**
 * 문의하기 관련 데이터베이스 연동을 처리하는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface SupportMapper {

	List<SupportDTO> getAllSupportWithAnswer(); // 문의글과 문의 답변 조회
	
	SupportDTO getSupportById(@Param("supportId") Long supportId); // 문의 아이디를 이용하여 문의글, 문의 답변 조회
	
	void insertSupport(SupportDTO supportDto); // 문의글 등록

	void updateStatus(String status); // 문의 상태 업데이트
}
