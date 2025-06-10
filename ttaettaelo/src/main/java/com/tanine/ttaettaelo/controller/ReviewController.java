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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.dto.ReviewDTO;
import com.tanine.ttaettaelo.service.ReviewService;

import lombok.RequiredArgsConstructor;

/**
 * 리뷰 관련 요청을 처리하는 컨트롤러 서비스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

	private final ReviewService reviewService;
	
	/**
	 * 리뷰 등록
	 * @param reviewDto 등록할 리뷰 정보
	 * @return 리뷰 등록 또는 리뷰 등록 실패 메시지
	 */
	@PostMapping("/reviews/submit")
	public ResponseEntity<String> submitReview(@RequestBody ReviewDTO reviewDto) {
		try {
			reviewService.saveReview(reviewDto); // 리뷰 저장
			return ResponseEntity.ok("리뷰가 등록되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 등록 실패");
		}
	}
	
	/**
	 * 리뷰 조회
	 * @param bathhouseInfoId 리뷰를 볼 목욕탕 정보글 번호
	 * @return 해당 목욕탕 정보글에 대한 리뷰 리스트
	 */
	@GetMapping("/reviews/{bathhouseInfoId}")
	public List<ReviewDTO> getReviews(@PathVariable(name = "bathhouseInfoId") Long bathhouseInfoId) {
		return reviewService.getReviewsByBathhouseId(bathhouseInfoId);
	}
	
	/**
	 * 리뷰 수정
	 * @param reviewDto 수정할 리뷰 정보
	 * @return 리뷰 수정 성공 메시지
	 */
    @PutMapping("/reviews/update")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO reviewDto) {
        reviewService.updateReview(reviewDto);
        return ResponseEntity.ok("리뷰 수정 성공");
    }

    /**
     * 리뷰 삭제
     * @param reviewId 삭제할 리뷰 번호
     * @return 리뷰 삭제 성공 메시지
     */
    @DeleteMapping("/reviews/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable(name = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰 삭제 성공");
    }
    
    /**
     * 내가 쓴 리뷰 조회
     * @param memberId 등록한 리뷰를 조회할 회원의 번호
     * @return 해당 회원이 등록한 리뷰 리스트
     */
    @GetMapping("/myReviews")
	public ResponseEntity<List<ReviewDTO>> getMyReviews(@RequestParam("memberId") Long memberId) {
        List<ReviewDTO> myReviews = reviewService.getMyReviews(memberId);
        return ResponseEntity.ok(myReviews);
    }
}
