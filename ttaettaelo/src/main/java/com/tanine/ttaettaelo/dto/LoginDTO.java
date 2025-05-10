package com.tanine.ttaettaelo.dto;

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
public class LoginDTO {
	
	private Long memberId;
	private String loginId;
	private String password;
	private String name;
	private String role;
}
