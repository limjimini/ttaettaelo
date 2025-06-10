package com.tanine.ttaettaelo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 리뷰 좋아요에 대한 정보를 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ReviewLikeDTO {

	@NotBlank
	private long reviewId; // 리뷰 일련번호
	
	@NotBlank
	private long memberId; // 회원 일련번호
}
