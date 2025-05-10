package com.tanine.ttaettaelo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SupportAnswerDTO {

	private Long answerId;
	private Long supportId;
	private Long memberId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
}
