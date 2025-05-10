package com.tanine.ttaettaelo.entity;

import java.util.Map;
import java.util.Set;

import com.tanine.ttaettaelo.converter.MapToJsonConverter;
import com.tanine.ttaettaelo.enums.BathhouseTag;
import com.tanine.ttaettaelo.enums.BathhouseType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@ToString
public class BathhouseInfoTag {

	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tagId; // 번호

	@Enumerated(EnumType.STRING)
	@NotNull
	private BathhouseTag tags;
		    
	// 외래키
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bathhouseId", nullable = false)
	private BathhouseInfo bathhouseInfo; // 게시글 번호
}
