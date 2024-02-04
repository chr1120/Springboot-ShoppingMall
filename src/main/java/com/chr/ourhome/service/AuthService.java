package com.chr.ourhome.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chr.ourhome.constant.Role;
import com.chr.ourhome.domain.member.Member;
import com.chr.ourhome.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service	// 1. IoC 등록 	2. 트랜잭션 관리
public class AuthService {
	
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public Member 회원가입(Member member) {
		// 회원가입 진행 <- Repository 필요
		// DB에 Insert
		String rawPassword=member.getPassword();
		String encPassword=bCryptPasswordEncoder.encode(rawPassword);
		member.setPassword(encPassword);
		member.setRole(Role.USER);  // 관리자는 Role.ADMIN
		
		Member memberEntity=memberRepository.save(member);
		return memberEntity;
	}
	
	public void validateDuplacateMember(Member member) {
		Member findMember=memberRepository.findByEmail(member.getEmail());
		if(findMember!=null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
}
