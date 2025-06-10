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

/**
 * 로그인을 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class LoginDTO {
	
	@NotBlank
	private Long memberId; // 회원 일련번호
	
	@NotBlank
	@Size(max = 50)
	private String loginId; // 로그인 아이디
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}")
	@NotBlank
	private String password; // 비밀번호
	
	@NotBlank
	@Size(max = 50)
	private String name; // 이름
	
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$")
	@NotBlank
	@Size(max = 100)
	private String email; // 이메일
	
	@NotNull
	@Size(max = 2)
	private String gender; // 성별
	
	@Size(max = 255)
	private String address; // 주소
}
