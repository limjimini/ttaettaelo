package com.tanine.ttaettaelo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.service.BathhouseInfoService;

import lombok.RequiredArgsConstructor;

/**
 * 목욕탕 정보글 관련 요청을 처리하는 컨트롤러 서비스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BathhouseInfoController {

	private final BathhouseInfoService bathhouseInfoService;
	
	/**
	 * 목욕탕 정보글 조회
	 * @return 목욕탕 정보글 리스트
	 */
	@GetMapping("/bathhouse")
	public List<BathhouseInfoDTO> getAllBathhouseInfo() {
		return bathhouseInfoService.getAllBathhouseInfo();
	}
	
	/**
	 * 어떤 한 목욕탕 정보글의 상세정보 조회
	 * @param bathhouseInfoId 상세정보를 보고 싶은 목욕탕 정보글의 번호
	 * @return 선택한 목욕탕의 상세정보
	 */
	@GetMapping("/bathhouse/{bathhouseInfoId}")
	public BathhouseInfoDTO getInfoDetailed(@PathVariable("bathhouseInfoId") Long bathhouseInfoId) {
		return bathhouseInfoService.getInfoDetailed(bathhouseInfoId);
	}
	
	/**
	 * 내가 좋아요한 목욕탕 정보글 조회
	 * @param memberId 조회하고 싶은 회원의 번호
	 * @return 회원이 좋아요한 목욕탕 정보글 리스트
	 */
	@GetMapping("/likedBathhouse")
	public ResponseEntity<List<BathhouseInfoDTO>> getLikedPosts(@RequestParam("memberId") Long memberId) {
        List<BathhouseInfoDTO> likedBathhouses = bathhouseInfoService.getLikedBathhouse(memberId);
        return ResponseEntity.ok(likedBathhouses);
    }
	
	/**
	 * 랜덤으로 뽑은 이미지 5개 조회
	 * @return 이미지 리스트
	 */
	@GetMapping("/randomImages")
	public ResponseEntity<List<String>> getRandomImages() {
		List<String> images = bathhouseInfoService.getRandomImages();
		return ResponseEntity.ok(images);
	}
}
