package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 목욕탕 좋아요 관련 데이터베이스 연동을 처리하는 마이바티스 매퍼 인터페이스
 */
@Mapper
public interface BathhouseInfoLikeMapper {

	boolean isLiked(@Param("bathhouseInfoId") Long bathhouseInfoId, @Param("memberId") Long memberId); // 좋아요 중복 확인
	
	void insertLike(@Param("bathhouseInfoId") Long bathhouseInfoId, @Param("memberId") Long memberId); // 좋아요 추가
	
	void deleteLike(@Param("bathhouseInfoId") Long bathhouseInfoId, @Param("memberId") Long memberId); // 좋아요 삭제
	
	int countLikes(Long bathhouseInfoId); // 목욕탕 정보글의 좋아요 총 개수
}
