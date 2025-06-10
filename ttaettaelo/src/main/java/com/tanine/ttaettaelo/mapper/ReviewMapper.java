package com.tanine.ttaettaelo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tanine.ttaettaelo.dto.ReviewDTO;

/**
 * 문의하기 관련 데이터베이스 연동을 처리하는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface ReviewMapper {

	void insertReview(ReviewDTO reviewDto); // 리뷰 등록
	
	List<ReviewDTO> getReviewsByBathhouseInfoId(Long bathhouseInfoId); // 리뷰 조회
	
	void updateReview(ReviewDTO reviewDto); // 리뷰 수정
	
	void deleteReview(Long reviewId); // 리뷰 삭제
	
	List<ReviewDTO> getMyReviews(Long memberId); // 내가 쓴 리뷰 조회
}
