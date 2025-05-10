package com.tanine.ttaettaelo.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString(exclude = {"bathhouseInfo", "member"})
@EntityListeners(AuditingEntityListener.class)
public class Review {

	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId; // 번호
	
	@Column(columnDefinition = "TEXT")
	private String content; // 리뷰 내용
	
	@NotNull
	private int rating; // 별점
	
//	@NotNull
//	private int likeCount = 0; // 댓글 좋아요 수(기본값:0)
	
	@NotNull
	@CreatedDate
	private LocalDateTime createdAt; // 생성일
	
	@LastModifiedDate
	private LocalDateTime modifiedAt; // 수정일
	
	// 외래키
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bathhouseId", nullable = false)
	private BathhouseInfo bathhouseInfo; // 게시글 번호
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = false)
	private Member member; // 아이디
	
    @OneToMany(mappedBy = "reviews", fetch = FetchType.LAZY)
    private Set<ReviewLike> reviewLikes;
}
