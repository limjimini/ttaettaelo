package com.tanine.ttaettaelo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.dto.ReviewDTO;
import com.tanine.ttaettaelo.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;

/**
 * 리뷰 관련 서비스를 처리하는 클래스
 * 리뷰 조회, 등록, 수정, 삭제, 내가 쓴 리뷰 리스트 가져오기와 같은 기능으 제공한다.
 */
@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewMapper reviewMapper;
	
	/**
	 * 리뷰 등록
	 * @param reviewDto 등록할 리뷰 정보
	 */
	public void saveReview(ReviewDTO reviewDto) {
		reviewMapper.insertReview(reviewDto);
	}
	
	/**
	 * 리뷰 조회
	 * @param bathhouseInfoId 목욕탕 정보글 번호
	 * @return 어떤 한 목욕탕 정보글의 리뷰 리스트
	 */
	public List<ReviewDTO> getReviewsByBathhouseId(Long bathhouseInfoId) {
		return reviewMapper.getReviewsByBathhouseInfoId(bathhouseInfoId);
	}
	
	/**
	 * 리뷰 수정
	 * @param reviewDto 수정할 리뷰 정보
	 */
	public void updateReview(ReviewDTO reviewDto) {
		reviewMapper.updateReview(reviewDto);
	}
	
	/**
	 * 리뷰 삭제
	 * @param reviewId 삭제할 리뷰 번호
	 */
	public void deleteReview(Long reviewId) {
		reviewMapper.deleteReview(reviewId);
	}
	
	/**
	 * 내가 쓴 리뷰를 모두 가져오기
	 * @param memberId 리뷰를 가져오고 싶은 회원 번호
	 * @return 회원 번호에 해당하는 회원이 쓴 리뷰 리스트
	 */
	public List<ReviewDTO> getMyReviews(Long memberId) {
		return reviewMapper.getMyReviews(memberId);
	}
}
