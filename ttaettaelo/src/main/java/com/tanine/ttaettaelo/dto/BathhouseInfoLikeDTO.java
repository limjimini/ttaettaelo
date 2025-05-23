package com.tanine.ttaettaelo.dto;

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
public class BathhouseInfoLikeDTO {

	private Long bathhouseInfoId;
	private Long memberId;
}
