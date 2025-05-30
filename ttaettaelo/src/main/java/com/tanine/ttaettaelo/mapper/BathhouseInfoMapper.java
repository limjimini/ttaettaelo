package com.tanine.ttaettaelo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.dto.BathhouseTagsForDetailedDTO;

@Mapper
public interface BathhouseInfoMapper {

	List<BathhouseInfoDTO> getAllBathhouseInfo(); // 목욕탕 정보글 조회
	
	BathhouseInfoDTO getInfoDetailed(Long bathhouseInfoId); // 목욕탕 정보상세글 조회
	
	List<BathhouseTagsForDetailedDTO> getTagByBAthhouseInfoId(Long bathhouseInfoId); // 목욕탕 정보상세글 태그 가져오기
	
	List<BathhouseInfoDTO> getLikedBathhouse(Long memberId);
	
	List<String> getRandomImages();
}
