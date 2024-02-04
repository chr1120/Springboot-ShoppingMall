package com.chr.ourhome.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.chr.ourhome.domain.member.Member;
import com.chr.ourhome.service.AuthService;
import com.chr.ourhome.web.dto.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		// final 필드를 DI할 때 사용. // final에 대한 모든 생성자로 만들어줌.
@Controller	// 1. IoC		2. 파일을 리턴하는 컨트롤러
public class AuthController {
		
	// DI <- AuthService가 IoC에 등록이 되어 있기 때문에 가능!! = @Autowired
	private final AuthService authService;
	
	@GetMapping("/member/signin")
	public String signinForm() {
		return "member/signin";
	}
	
	@GetMapping("/member/signup")
	public String signupForm(Model model) {
		model.addAttribute("signupDto", new SignupDto());		
		return "member/signup";
	}
	
	@GetMapping("/")
	public String signin() {
		return "layouts/layout1";
	}
	
	// 회원가입 버튼 - /member/signup -> /
	@PostMapping("/member/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult, Model model) {	// key=value (x-www-form-urlencoded)
		// Member <- SignupDto
		
		if(bindingResult.hasErrors()) {
			return "member/signup";
		}
		
		try {
			Member member=signupDto.toEntity();
			authService.회원가입(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/signup";
		}
		return "redirect:/";
	}
}
