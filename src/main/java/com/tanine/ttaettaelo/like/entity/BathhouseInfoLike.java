package com.tanine.ttaettaelo.like.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tanine.ttaettaelo.bathhouseInfo.entity.BathhouseInfo;
import com.tanine.ttaettaelo.like.enums.LikeType;
import com.tanine.ttaettaelo.member.entity.Member;
import com.tanine.ttaettaelo.review.entity.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"member", "bathhouseInfo"})
@EntityListeners(AuditingEntityListener.class)
public class BathhouseInfoLike {

	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bathhouseInfoLikeId; // 번호
	
    @NotNull
    @CreatedDate
    private LocalDateTime createdAt; // 생성일
	
	// 외래키	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = false)
	private Member member; // 아이디
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bathhouseId", nullable = false)
	private BathhouseInfo bathhouseInfo; // 게시글 번호
}
