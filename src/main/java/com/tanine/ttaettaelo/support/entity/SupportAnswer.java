package com.tanine.ttaettaelo.support.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tanine.ttaettaelo.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@ToString(exclude = {"support"})
@EntityListeners(AuditingEntityListener.class)
public class SupportAnswer {

	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supportAnswerId; // 번호
	
	@NotNull
	private String writer; // 작성자
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String title; // 문의하기 답변 제목
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String content; // 문의하기 답변 내용
	
	@NotNull
	@CreatedDate
	private LocalDateTime createdAt; // 생성일
	
	@LastModifiedDate
	private LocalDateTime modifiedAt; // 수정일
	
	// 외래키
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supportId", nullable = false)
	private Support support; // 문의하기 번호
}
