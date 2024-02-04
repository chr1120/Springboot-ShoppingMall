package com.chr.ourhome.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chr.ourhome.domain.member.Member;
import com.chr.ourhome.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service	// IoC 등록
public class PrincipalDetailsService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member memberEntity=memberRepository.findByEmail(email);
		
		if(memberEntity==null) {
			return null;
		}else {
			return new PrincipalDetails(memberEntity);
		}
	}
}
