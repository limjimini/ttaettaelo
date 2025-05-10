package com.tanine.ttaettaelo.dto;

import java.util.List;

import com.tanine.ttaettaelo.enums.BathhouseType;

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
public class BathhouseTagDTO {

	private Long bathhouseInfoId;
	private Long tagId;
	private String tagName;
}
