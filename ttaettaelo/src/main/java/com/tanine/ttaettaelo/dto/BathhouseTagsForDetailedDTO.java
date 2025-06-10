package com.tanine.ttaettaelo.dto;

import jakarta.validation.constraints.NotBlank;
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
public class BathhouseTagsForDetailedDTO {
	
	@NotBlank
	private long bathhouseInfoId; // 목욕탕 정보글 일련번호
	
	@NotBlank
	private long tagId; // 목욕탕 태그 일련번호
	
	private String tagName; // 목욕탕 태그
}
