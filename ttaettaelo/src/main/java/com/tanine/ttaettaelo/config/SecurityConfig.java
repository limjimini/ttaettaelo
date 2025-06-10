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

/**
 * Spring Security 설정
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.cors(Customizer.withDefaults()) // CORS 설정 활성화
		.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
		.authorizeHttpRequests((authorize) -> authorize
//			.requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 가능
			.anyRequest().permitAll()); // 그 외 페이지는 누구나 접근 가능
		return httpSecurity.build();        
	}
	
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // 사용자 인증 처리 시 필요한 서비스
        auth.inMemoryAuthentication() // 메모리에 사용자 정보 저장
            .withUser("user") // 사용자명
            .password(passwordEncoder().encode("password")) // 암호화된 비밀번호
            .roles("USER"); // 사용자 역할
    }

	@Bean
	public PasswordEncoder passwordEncoder() { // 비밀번호 암호화를 위한 Bean 등록
		return new BCryptPasswordEncoder();
	}
}
