package com.tanine.ttaettaelo.dto;

import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 목욕탕 정보글 관련 정보를 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BathhouseInfoDTO {

	@NotBlank
	private Long bathhouseInfoId; // 목욕탕 정보글 일련번호
	
	@NotBlank
	@Size(max = 100)
	private String name; // 목욕탕 이름
	
	private String introduction; // 목욕탕 소개
	
	@NotBlank
	@Size(max = 10)
	private String type; // 목욕탕 타입(목욕탕, 사우나, 찜질방)
	
	@NotBlank
	@Size(max = 255)
	private String location; // 위치
	
	private LocalTime startTime; // 시작 시간
	
	private LocalTime endTime; // 마치는 시간
	
	private String closedDay; // 휴무일
	
	@Size(max = 20)
	private String contactNumber; // 연락처
	
	@Size(max = 255)
	private String imgUrl; // 이미지
	
	private double avgRating; // 평균 평점
	private int reviewCount; // 리뷰 수
	private int likeCount; // 좋아요 수
	
	private List<BathhouseTagDTO> tagKeyword; // 목욕탕 태그
	private List<BathhouseTagsForDetailedDTO> tags; // 목욕탕 태그 리스트
//	private List<ReviewDTO> reviews; // 리뷰 리스트
//	private Long bathhouseInfoLikeCount; // 좋아요 수
}
