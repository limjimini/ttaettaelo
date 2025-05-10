package com.tanine.ttaettaelo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.ReviewDTO;
import com.tanine.ttaettaelo.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	
	// 리뷰 등록
	@PostMapping("/reviews/submit")
	public ResponseEntity<String> submitReview(@RequestBody ReviewDTO reviewDto) {
		try {
			reviewService.saveReview(reviewDto);
			return ResponseEntity.ok("리뷰가 등록되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 등록 실패");
		}
	}
	
	// 리뷰 조회
	@GetMapping("/reviews/{bathhouseInfoId}")
	public List<ReviewDTO> getReviews(@PathVariable Long bathhouseInfoId) {
		return reviewService.getReviewsByBathhouseId(bathhouseInfoId);
	}
}
