package com.tanine.ttaettaelo.dto;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 리뷰에 대한 정보를 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {

	@NotBlank
	private Long reviewId; // 리뷰 일련번호
	
	@NotBlank
	private Long bathhouseInfoId; // 목욕탕 정보글 일련번호
	
	@NotBlank
	private Long memberId; // 회원 일련번호
	
	@Nullable
	@Size(max = 110)
	private String content; // 내용
	
	@NotBlank
	private Integer rating; // 평점
	
	@NotBlank
	private LocalDateTime createdAt; // 생성일
	
	@NotBlank
	private LocalDateTime updatedAt; // 수정일
	
	private String name; // 이름(회원)
	private int likeCount; // 좋아요 수
}
