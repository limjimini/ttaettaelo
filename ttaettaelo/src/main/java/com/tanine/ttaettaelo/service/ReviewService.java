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
}
