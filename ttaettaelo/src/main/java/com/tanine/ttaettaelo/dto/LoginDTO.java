package com.tanine.ttaettaelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	private String email;
	private String gender;
	private String address;
//	private String role;
}
