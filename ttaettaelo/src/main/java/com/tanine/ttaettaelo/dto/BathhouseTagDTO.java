package com.tanine.ttaettaelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 목욕탕 태그를 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BathhouseTagDTO {

	@NotBlank
	@Size(max = 10)
	private String tagName; // 목욕탕 태그
}
