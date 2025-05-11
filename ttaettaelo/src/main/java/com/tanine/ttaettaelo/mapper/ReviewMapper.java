package com.tanine.ttaettaelo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tanine.ttaettaelo.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {

	void insertReview(ReviewDTO reviewDto); // 리뷰 등록
	
	List<ReviewDTO> getReviewsByBathhouseInfoId(Long bathhouseInfoId); // 리뷰 조회
	
	void updateReview(ReviewDTO reviewDto);
	
	void deleteReview(Long reviewId);
}
