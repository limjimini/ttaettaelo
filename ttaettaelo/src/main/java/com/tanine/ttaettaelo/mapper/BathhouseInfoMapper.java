package com.tanine.ttaettaelo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.dto.BathhouseTagsForDetailedDTO;

/**
 * 목욕탕 정보글 관련 데이터베이스 연동을 처리하는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface BathhouseInfoMapper {

	List<BathhouseInfoDTO> getAllBathhouseInfo(@Param("name") String name, @Param("location") String location, @Param("type") String type, @Param("tagName") String tagName); // 목욕탕 정보글 조회
		
	BathhouseInfoDTO getInfoDetailed(Long bathhouseInfoId); // 목욕탕 정보상세글 조회
	
	List<BathhouseTagsForDetailedDTO> getTagByBAthhouseInfoId(Long bathhouseInfoId); // 목욕탕 정보상세글 태그 가져오기
	
	List<BathhouseInfoDTO> getLikedBathhouse(Long memberId); // 내가 좋아요한 목욕탕 리스트 가져오기
	
	List<String> getRandomImages(); // 랜덤으로 이미지 5개 가져오기
}
