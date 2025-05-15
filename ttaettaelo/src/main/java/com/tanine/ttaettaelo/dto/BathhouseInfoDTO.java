package com.tanine.ttaettaelo.dto;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class BathhouseInfoDTO {

	private Long bathhouseInfoId;
	private String name;
	private String introduction;
	private BathhouseType type;
	private String location;
	private LocalTime startTime;
	private LocalTime endTime;
	private String closedDay;
	private String contactNumber;
	private String imgUrl;
	
	private double avgRating;
	private int reviewCount;
	private int likeCount;
	
	private List<BathhouseTagDTO> tagKeyword;
	private List<BathhouseTagsForDetailedDTO> tags;
	private List<ReviewDTO> reviews;
	private Long bathhouseInfoLikeCount;
}
