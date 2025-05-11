package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewLikeMapper {
	
	boolean isLiked(@Param("reviewId") long reviewId, @Param("memberId") long memberId);
	
	void insertLike(@Param("reviewId") long reviewId, @Param("memberId") long memberId);
	
	void deleteLike(@Param("reviewId") long reviewId, @Param("memberId") long memberId);
	
	int countLikes(long bathhouseInfoId);
}
