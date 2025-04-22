package com.tanine.ttaettaelo.member.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tanine.ttaettaelo.bathhouseInfo.enums.BathhouseType;
import com.tanine.ttaettaelo.like.entity.BathhouseInfoLike;
import com.tanine.ttaettaelo.like.entity.ReviewLike;
import com.tanine.ttaettaelo.member.enums.Gender;
import com.tanine.ttaettaelo.review.entity.Review;
import com.tanine.ttaettaelo.support.entity.Support;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 번호
    
    @NotNull
    @Column(unique = true)
    private String loginId; // 아이디
    
    @NotNull
    private String password; // 비밀번호
    
    @NotNull
    private String name; // 이름
    
    @NotNull
    private String email; // 이메일
    
    @NotNull
    private String phoneNumber; // 전화번호
    
	@Enumerated(EnumType.STRING)
	private Gender gender; // 성별
    
    private String address;// 주소
    
    @NotNull
    private boolean isDeleted = false; // 탈퇴 여부(기본값:false/true -> 탈퇴, false -> 회원)
    
    @NotNull
    @CreatedDate
    private LocalDateTime createdAt; // 생성일
    
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Review> reviews;
    
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<ReviewLike> reviewLikes;
    
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<BathhouseInfoLike> bathhouseInfoLikes;
    
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Support> supports;
}
