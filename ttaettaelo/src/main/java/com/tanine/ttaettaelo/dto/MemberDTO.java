package com.tanine.ttaettaelo.dto;

import com.tanine.ttaettaelo.enums.Gender;

import jakarta.validation.constraints.Max;
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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
	
	private Long memberId;

	@NotBlank(message = "필수 입력입니다.")
	private String loginId;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자, 영문대소문자, 숫자, 특수문자를 사용해주세요.")
	@NotBlank(message = "필수 입력입니다.")
	private String password;
	
	@NotBlank(message = "비밀번호 확인은 필수입니다.")
	private String passwordCheck;
	
	@Size(max = 6, message = "이름은 최대 6자까지 입력 가능합니다.")
	@NotBlank(message = "필수 입력입니다.")
	private String name;
	
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 정확하지 않습니다.")
	@NotBlank(message = "필수 입력입니다.")
	private String email;
	
	@NotNull(message = "3개 중에 선택해주세요.")
	private Gender gender;
	
	private String address;	
	
	public boolean isPasswordChecked() {
		return password != null && password.equals(passwordCheck);
	}
}
