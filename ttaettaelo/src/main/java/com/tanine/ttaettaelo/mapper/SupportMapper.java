package com.tanine.ttaettaelo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tanine.ttaettaelo.dto.SupportAnswerDTO;
import com.tanine.ttaettaelo.dto.SupportDTO;

@Mapper
public interface SupportMapper {

	List<SupportDTO> getAllSupportWithAnswer();
	
	SupportDTO getSupportById(@Param("supportId") Long supportId);
	
	void insertSupport(SupportDTO supportDto);

	void updateStatus(String status);
}
