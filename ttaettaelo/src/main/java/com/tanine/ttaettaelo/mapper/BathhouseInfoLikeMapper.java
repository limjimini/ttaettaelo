package com.tanine.ttaettaelo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BathhouseInfoLikeMapper {

	boolean isLiked(@Param("bathhouseInfoId") Long bathhouseInfoId, @Param("memberId") Long memberId);
	
	void insertLike(@Param("bathhouseInfoId") Long bathhouseInfoId, @Param("memberId") Long memberId);
	
	void deleteLike(@Param("bathhouseInfoId") Long bathhouseInfoId, @Param("memberId") Long memberId);
	
	int countLikes(Long bathhouseInfoId);
}
