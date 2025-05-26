package com.tanine.ttaettaelo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.dto.ReviewDTO;
import com.tanine.ttaettaelo.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewMapper reviewMapper;
	
	// 리뷰 등록
	public void saveReview(ReviewDTO reviewDto) {
		reviewMapper.insertReview(reviewDto);
	}
	
	// 리뷰 조회
	public List<ReviewDTO> getReviewsByBathhouseId(Long bathhouseInfoId) {
		return reviewMapper.getReviewsByBathhouseInfoId(bathhouseInfoId);
	}
	
	// 리뷰 수정
	public void updateReview(ReviewDTO reviewDto) {
		reviewMapper.updateReview(reviewDto);
	}
	 // 리뷰 삭제
	public void deleteReview(Long reviewId) {
		reviewMapper.deleteReview(reviewId);
	}
	
	// 내가 쓴 리뷰 리스트 가져오기
	public List<ReviewDTO> getMyReviews(Long memberId) {
		return reviewMapper.getMyReviews(memberId);
	}
}
