package com.tanine.ttaettaelo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.service.BathhouseInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
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
}
