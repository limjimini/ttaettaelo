package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 리뷰 좋아요 관련 데이터베이스 연동을 처리하는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface ReviewLikeMapper {
	
	boolean isLiked(@Param("reviewId") long reviewId, @Param("memberId") long memberId); // 좋아요 중복 확인
	
	void insertLike(@Param("reviewId") long reviewId, @Param("memberId") long memberId); // 좋아요 추가
	
	void deleteLike(@Param("reviewId") long reviewId, @Param("memberId") long memberId); // 좋아요 삭제
	
	int countLikes(long bathhouseInfoId); // 리뷰 좋아요 총 개수
}
