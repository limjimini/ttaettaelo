package com.tanine.ttaettaelo.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 문의글의 정보를 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SupportDTO {

	@NotBlank
	private Long supportId; // 문의글 일련번호
	
	@NotBlank
	private Long memberId; // 회원 일련번호
	
	@NotBlank
	@Size(max = 100)
	private String title; // 문의 제목
	
	@NotBlank
	@Size(max = 255)
	private String content; // 문의 내용
	
	@NotBlank
	@Size(max = 5)
	private String status; // 문의 상태
	
	@NotBlank
	private LocalDateTime createdAt; // 생성일
	
	private String name; // 작성자 이름
	private Long answerId; // 문의 답변 일련번호
	private String answerContent; // 문의 답변 내용
	private LocalDateTime answerCreatedAt; // 문의 답변 생성일
}
