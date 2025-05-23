package com.tanine.ttaettaelo.dto;

import java.time.LocalTime;
import java.util.List;

import com.tanine.ttaettaelo.enums.BathhouseType;

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
public class BathhouseTagDTO {

//	private long tagId;
//	private String category;
	private String tagName;
}
