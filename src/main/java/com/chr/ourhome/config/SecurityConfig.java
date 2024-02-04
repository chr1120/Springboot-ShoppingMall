package com.chr.ourhome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration	// IoC
@EnableWebSecurity	// 해당 파일로 Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http); // 기존 시큐리티의 기능 모두 비활성화
		// 이제 기존 시큐리티가 localhost:8080을 낚아채지 못함.
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/", "/member/**", "/item/**",
					"/images/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin()
			.loginPage("/member/signin")
			.defaultSuccessUrl("/")
			.usernameParameter("email")
			.failureUrl("/member/siginin/error")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.logoutSuccessUrl("/");
	}
	
	// static 디렉토리의 하위 파일은 인증을 무시하도록 설정!!
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
}
