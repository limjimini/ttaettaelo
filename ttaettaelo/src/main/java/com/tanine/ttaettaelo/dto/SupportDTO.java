package com.tanine.ttaettaelo.dto;

import java.time.LocalDateTime;
import java.util.List;

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
public class SupportDTO {

	private Long supportId;
	private Long memberId;
	private String title;
	private String content;
	private String status;
	private LocalDateTime createdAt;
	private String name;
	private Long answerId;
	private String answerContent;
	private LocalDateTime answerCreatedAt;
}
