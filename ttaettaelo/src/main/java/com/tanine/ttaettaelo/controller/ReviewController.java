package com.tanine.ttaettaelo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.ReviewDTO;
import com.tanine.ttaettaelo.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/reviews")
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
	public List<ReviewDTO> getReviews(@PathVariable(name = "bathhouseInfoId") Long bathhouseInfoId) {
		return reviewService.getReviewsByBathhouseId(bathhouseInfoId);
	}
	
    @PutMapping("/reviews/update")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO reviewDto) {
        reviewService.updateReview(reviewDto);
        return ResponseEntity.ok("리뷰 수정 성공");
    }

    @DeleteMapping("/reviews/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable(name = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰 삭제 성공");
    }
}
