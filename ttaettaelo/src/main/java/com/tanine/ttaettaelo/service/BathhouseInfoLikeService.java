package com.tanine.ttaettaelo.service;

import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.mapper.BathhouseInfoLikeMapper;

import lombok.RequiredArgsConstructor;

/**
 * 목욕탕 좋아요 관련 서비스를 처리하는 클래스
 * 목욕탕 좋아요 추가/삭제 및 목욕탕 좋아요 개수 구하기와 같은 기능을 제공한다.
 */
@Service
@RequiredArgsConstructor
public class BathhouseInfoLikeService {

	private final BathhouseInfoLikeMapper bathhouseInfoLikeMapper;

	/**
	 * 목욕탕 좋앙 추가/삭제
	 * @param bathhouseInfoId 좋아요를 추가하거나 삭제할 목욕탕 정보글 번호
	 * @param memberId 좋아요 추가/삭제를 하는 회원 번호
	 * @return 좋아요 상태(추가/삭제)
	 */
	public boolean bathhouseLike(Long bathhouseInfoId, Long memberId) {
		if(bathhouseInfoLikeMapper.isLiked(bathhouseInfoId, memberId)) {
			bathhouseInfoLikeMapper.deleteLike(bathhouseInfoId, memberId);
			return false; // 좋아요 삭제
		} else {
			bathhouseInfoLikeMapper.insertLike(bathhouseInfoId, memberId);
			return true; // 좋아요 추가
		}
	}
	
	/**
	 * 목욕탕에 대한 좋아요 수
	 * @param bathhouseInfoId 좋아요 총 개수를 구할 목욕탕 정보글 번호
	 * @return 해당 목욕탕 정보글의 좋아요 개수
	 */
	public int getLikeCount(Long bathhouseInfoId) {
		return bathhouseInfoLikeMapper.countLikes(bathhouseInfoId);
	}
}
