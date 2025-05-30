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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BathhouseInfoController {

	private final BathhouseInfoService bathhouseInfoService;
	
	// 게시물 조회
	@GetMapping("/bathhouse")
	public List<BathhouseInfoDTO> getAllBathhouseInfo() {
		return bathhouseInfoService.getAllBathhouseInfo();
	}
	
	// 게시글의 상세페이지 조회
	@GetMapping("/bathhouse/{bathhouseInfoId}")
	public BathhouseInfoDTO getInfoDetailed(@PathVariable("bathhouseInfoId") Long bathhouseInfoId) {
		return bathhouseInfoService.getInfoDetailed(bathhouseInfoId);
	}
	
	// 내가 좋아요한 목욕탕 게시글 조회
	@GetMapping("/likedBathhouse")
	public ResponseEntity<List<BathhouseInfoDTO>> getLikedPosts(@RequestParam("memberId") Long memberId) {
        List<BathhouseInfoDTO> likedBathhouses = bathhouseInfoService.getLikedBathhouse(memberId);
        return ResponseEntity.ok(likedBathhouses);
    }
	
	// 홈 화면에 나타낼 이미지 5개
	@GetMapping("/randomImages")
	public ResponseEntity<List<String>> getRandomImages() {
		List<String> images = bathhouseInfoService.getRandomImages();
		return ResponseEntity.ok(images);
	}
}
