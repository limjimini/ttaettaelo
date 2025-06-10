package com.tanine.ttaettaelo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS 설정을 위한 클래스
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경롱네 CORS 설정 적용
    	.allowedOrigins("http://localhost:8082") // 허용할 출처
//    	.allowedOrigins("http://localhost:3000") // 허용할 출처
        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP method
        .allowedHeaders("*") // 모든 요청 헤더 허용
        .allowCredentials(true); // 쿠키 인증 요청 허용
//        .maxAge(3000) // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {  // CORS 설정을 위한 Bean 생성
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:8082")); // Vue 앱 주소
//		configuration.setAllowedOrigins(List.of("http://localhost:3000")); // React 앱 주소
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 허용할 HTTP 메서드
		configuration.setAllowedHeaders(List.of("*")); // 모든 요청 헤더 허용
		configuration.setAllowCredentials(true); // 세션 쿠키 전달 허용

		// CORS 설정을 URL에 등록
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
