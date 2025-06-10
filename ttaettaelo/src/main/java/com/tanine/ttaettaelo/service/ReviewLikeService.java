package com.tanine.ttaettaelo.service;

import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.mapper.ReviewLikeMapper;

import lombok.RequiredArgsConstructor;

/**
 * 리뷰 좋아요 관련 서비스를 처리하는 클래스
 * 리뷰 좋아요 추가/삭제 및 좋아요 총 개수를 얻기와 같은 기능을 제공한다.
 */
@Service
@RequiredArgsConstructor
public class ReviewLikeService {

	private final ReviewLikeMapper reviewLikeMapper;
	
	/**
	 * 리뷰 좋아요 추가/삭제
	 * @param reviewId 좋아요를 추가하거나 삭제할 리뷰 번호
	 * @param memberId 좋아요 추가/삭제를 하는 회원 번호
	 * @return 좋아요 상태(추가/삭제)
	 */
	public boolean reviewLike(long reviewId, long memberId) {
		if(reviewLikeMapper.isLiked(reviewId, memberId)) {
			reviewLikeMapper.deleteLike(reviewId, memberId);
			return false; // 좋아요 삭제
		} else {
			reviewLikeMapper.insertLike(reviewId, memberId);
			return true; // 좋아요 추가
		}
	}
	
	/**
	 * 리뷰에 대한 좋아요 수
	 * @param reviewId 좋아요 총 개수를 구할 리뷰 번호
	 * @return 해당 리뷰의 좋아요 개수
	 */
	public int getLikeCount(long reviewId) {
		return reviewLikeMapper.countLikes(reviewId);
	}
}
