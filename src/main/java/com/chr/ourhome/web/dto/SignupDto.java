package com.chr.ourhome.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.chr.ourhome.constant.Role;
import com.chr.ourhome.domain.member.Member;

import lombok.Data;

@Data
public class SignupDto {
	
	@NotBlank(message="이름은 필수 입력 값입니다.")
	private String name;
	
	@NotEmpty(message="이메일은 필수 입력 값입니다.")
	@Email(message="이메일 형식으로 입력해주세요.")
	private String email;
	
	@NotEmpty(message="비밀번호는 필수 입력 값입니다.")
	@Length(min=8, max=16, message="비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
	private String password;

	@NotEmpty(message="주소는 필수 입력 값입니다.")
	private String address;
	
	private Role role;
	
	public Member toEntity() {
		return Member.builder()
				.name(name)
				.password(password)
				.email(email)
				.address(address)
				.role(Role.USER)
				.build();
	}
}
