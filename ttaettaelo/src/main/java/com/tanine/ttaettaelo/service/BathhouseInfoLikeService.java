package com.tanine.ttaettaelo.service;

import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.mapper.BathhouseInfoLikeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BathhouseInfoLikeService {

	private final BathhouseInfoLikeMapper bathhouseInfoLikeMapper;
	
	public boolean bathhouseLike(Long bathhouseInfoId, Long memberId) { // 좋아요 실행/취소
		if(bathhouseInfoLikeMapper.isLiked(bathhouseInfoId, memberId)) {
			bathhouseInfoLikeMapper.deleteLike(bathhouseInfoId, memberId);
			return false; // 좋아요 취소
		} else {
			bathhouseInfoLikeMapper.insertLike(bathhouseInfoId, memberId);
			return true; // 좋아요 추가
		}
	}
	
	public int getLikeCount(Long bathhouseInfoId) { // 목욕탕 총 좋아요 수
		return bathhouseInfoLikeMapper.countLikes(bathhouseInfoId);
	}
}
