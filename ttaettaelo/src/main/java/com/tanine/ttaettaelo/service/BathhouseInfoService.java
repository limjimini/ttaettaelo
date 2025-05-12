package com.tanine.ttaettaelo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.dto.BathhouseTagsForDetailedDTO;
import com.tanine.ttaettaelo.mapper.BathhouseInfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BathhouseInfoService {

	private final BathhouseInfoMapper bathhouseInfoMapper;
	
	// 목욕탕 정보글 조회
	public List<BathhouseInfoDTO> getAllBathhouseInfo() {
		return bathhouseInfoMapper.getAllBathhouseInfo();
	}
	
	// 목욕탕 정보상세글 조회
	public BathhouseInfoDTO getInfoDetailed(@PathVariable Long bathhouseInfoId) {
		BathhouseInfoDTO bathhouse = bathhouseInfoMapper.getInfoDetailed(bathhouseInfoId);
		
		List<BathhouseTagsForDetailedDTO> tags = bathhouseInfoMapper.getTagByBAthhouseInfoId(bathhouseInfoId);
		bathhouse.setTags(tags);
		return bathhouse;
	}
}
