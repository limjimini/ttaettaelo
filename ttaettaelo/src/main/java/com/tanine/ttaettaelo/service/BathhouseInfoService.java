package com.tanine.ttaettaelo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.dto.BathhouseTagsForDetailedDTO;
import com.tanine.ttaettaelo.mapper.BathhouseInfoMapper;

import lombok.RequiredArgsConstructor;

/**
 * 목욕탕 정보글 관련 서비스를 처리하는 클래스
 * 목욕탕 정보글 조회, 상세페이지 조회,
 * 좋아요한 목욕탕 정보글 조회, 랜덤으로 이미지 가져오기와 같은 기능을 제공한다.
 */
@Service
@RequiredArgsConstructor
public class BathhouseInfoService {

	private final BathhouseInfoMapper bathhouseInfoMapper;
	
	/**
	 * 목욕탕 정보글 조회
	 * @return 목욕탕 정보글 리스트
	 */
	public List<BathhouseInfoDTO> getAllBathhouseInfo() {
		return bathhouseInfoMapper.getAllBathhouseInfo();
	}
	
	/**
	 * 어떤 한 목욕탕의 상세페이지 조회
	 * @param bathhouseInfoId 상세페이지를 조회할 목욕탕 번호
	 * @return 해당하는 목욕탕의 상세 정보
	 */
	public BathhouseInfoDTO getInfoDetailed(@PathVariable Long bathhouseInfoId) {
		BathhouseInfoDTO bathhouse = bathhouseInfoMapper.getInfoDetailed(bathhouseInfoId); // 목욕탕 상세 정보 조회
		
		// 목욕탕 태그 조회
		List<BathhouseTagsForDetailedDTO> tags = bathhouseInfoMapper.getTagByBAthhouseInfoId(bathhouseInfoId);
		bathhouse.setTags(tags);
		
		return bathhouse;
	}
	
	/**
	 * 내가 좋아요한 목욕탕 정보글 조회
	 * @param memberId 목욕탕을 조회할 회원 번호
	 * @return 회원이 좋아요 누른 목욕탕 정보글 리스트
	 */
	public List<BathhouseInfoDTO> getLikedBathhouse(Long memberId) {
		return bathhouseInfoMapper.getLikedBathhouse(memberId);
	}
	
	/**
	 * 랜덤으로 이미지 조회
	 * @return 랜덤으로 뽑은 이미지
	 */
	public List<String> getRandomImages() {
		return bathhouseInfoMapper.getRandomImages();
	}
}
