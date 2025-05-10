package com.tanine.ttaettaelo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {

	private Long reviewId;
	private Long bathhouseInfoId;
	private Long memberId;
	private String content;
	private Integer rating;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private String name;
}
