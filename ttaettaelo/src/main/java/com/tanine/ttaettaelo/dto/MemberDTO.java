package com.tanine.ttaettaelo.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원가입 및 회원정보를 전송하는 데이터 객체
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
	
	@NotBlank
	private Long memberId; // 회원 일련번호

	@NotBlank(message = "필수 입력입니다.")
	@Size(max = 50)
	private String loginId; // 로그인 아이디
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자, 영문대소문자, 숫자, 특수문자를 사용해주세요.")
	@NotBlank(message = "필수 입력입니다.")
	private String password; // 비밀번호
	
	@NotBlank(message = "비밀번호 확인은 필수입니다.")
	private String passwordCheck; // 비밀번호 확인
	
	@Size(max = 6, message = "이름은 최대 6자까지 입력 가능합니다.")
	@NotBlank(message = "필수 입력입니다.")
	private String name; // 이름
	
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 정확하지 않습니다.")
	@NotBlank(message = "필수 입력입니다.")
	@Size(max = 100)
	private String email; // 이메일 주소
	
	@NotNull(message = "3개 중에 선택해주세요.")
	@Size(max = 2)
	private String gender; // 성별
	
	@Nullable
	private String address; // 주소
	
	public boolean isPasswordChecked() { // 비밀번호 일치 여부
		return password != null && password.equals(passwordCheck);
	}
}
