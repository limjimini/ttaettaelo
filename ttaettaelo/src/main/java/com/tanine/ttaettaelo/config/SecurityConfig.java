package com.tanine.ttaettaelo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		
		httpSecurity
		.cors(Customizer.withDefaults())
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((authorize) -> authorize
//			.requestMatchers("/support/**").authenticated() // /support 인증 필요
//			.requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 가능
			.anyRequest().permitAll()); // 그 외 페이지는 누구나 접근 가능
		return httpSecurity.build();
		
//		http
////			.csrf(csrf ->csrf.disable()) // CSRF 보호 비활성화
//			.authorizeHttpRequests(requests ->requests
//				.requestMatchers("/login", "/signup", "/guide/**", "/bathhouse/**").permitAll() // 로그인, 회원가입, 목욕탕 가이드, 목욕탕 정보 페이지는 인증 없이 접근 가능
////				.requestMatchers("/admin/**").hasRole("ADMIN") // /admin/* 경로는 관리자 권한만 접근 가능
//				.anyRequest().authenticated()) //나머지 페이지는 인증된 사용자만 접근 가능
//			.formLogin(login ->login
//				.loginPage("/login") // 로그인 페이지 설정
//				.loginProcessingUrl("/login") // 로그인 폼 제출
//				.defaultSuccessUrl("/", true) // 로그인 성공시 홈 화면으로
//				.failureUrl("/login") // 로그인 실패시 로그인 화면으로
//				.permitAll()) // 로그인 페이지는 인증 없이 접근 가능
//			.logout(logout ->logout
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL 설정
//				.logoutSuccessUrl("/logout") // 로그아웃 성공시 로그아웃 화면으로
//				.permitAll()); // 로그아웃 페이지는 인증 없이 접근 가능
//		return http.build();
        
	}
	
    // 사용자 인증 처리 시 필요한 서비스
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
