package com.tanine.ttaettaelo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 목욕탕 정보글의 좋아요를 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BathhouseInfoLikeDTO {

	@NotBlank
	private Long bathhouseInfoId; // 목욕탕 정보글 일련번호
	
	@NotBlank
	private Long memberId; // 회원 일련번호
}
