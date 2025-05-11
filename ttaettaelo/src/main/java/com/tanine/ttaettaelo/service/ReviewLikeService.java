package com.tanine.ttaettaelo.service;

import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.mapper.ReviewLikeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {

	private final ReviewLikeMapper reviewLikeMapper;
	
	public boolean reviewLike(long reviewId, long memberId) {
		if(reviewLikeMapper.isLiked(reviewId, memberId)) {
			reviewLikeMapper.deleteLike(reviewId, memberId);
			return false; // 좋아요 취소
		} else {
			reviewLikeMapper.insertLike(reviewId, memberId);
			return true; // 좋아요 추가
		}
	}
	
	public int getLikeCount(long reviewId) {
		return reviewLikeMapper.countLikes(reviewId);
	}
}
