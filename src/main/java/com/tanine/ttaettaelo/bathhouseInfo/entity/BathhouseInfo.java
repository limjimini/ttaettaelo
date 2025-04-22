package com.tanine.ttaettaelo.bathhouseInfo.entity;

import java.util.Map;
import java.util.Set;

import com.tanine.ttaettaelo.bathhouseInfo.converter.MapToJsonConverter;
import com.tanine.ttaettaelo.bathhouseInfo.enums.BathhouseType;
import com.tanine.ttaettaelo.like.entity.BathhouseInfoLike;
import com.tanine.ttaettaelo.review.entity.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BathhouseInfo {
	
	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bathhouseInfoId; // 번호
	
	@NotNull
	private String name; // 목욕탕 가게 이름
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String introduction; // 목욕탕 설명
	
	@NotNull
	private String location; // 목욕탕 위치
	
	@Convert(converter = MapToJsonConverter.class)
	@Column(columnDefinition = "JSON")
	private Map<String, String> businessHours; // 영업시간
	
	private String contactNumber; // 전화번호
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private BathhouseType type; // 목욕탕 타입
	
	@Column(columnDefinition = "TEXT")
	private String imgUrl; // 목욕탕 사진
	
//	@Column(columnDefinition = "TEXT")
//	private String socialMediaUrl; // 목욕탕 SNS
	
    @OneToMany(mappedBy = "bathhouseInfo", fetch = FetchType.LAZY)
    private Set<Review> reviews;
    
    @OneToMany(mappedBy = "bathhouseInfo", fetch = FetchType.LAZY)
    private Set<BathhouseInfoLike> bathhouseInfoLikes;
    
    @OneToMany(mappedBy = "bathhouseInfo", fetch = FetchType.LAZY)
    private Set<BathhouseInfoTag> bathhouseInfoTags;

}
