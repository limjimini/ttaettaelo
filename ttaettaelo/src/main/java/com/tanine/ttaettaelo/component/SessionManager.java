package com.tanine.ttaettaelo.component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionManager {

	public static final String SESSION_COOKIE_NAME = "mySessionId";
	
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
	
	/**
	 * 세션 생성
	 * @param value 세션에 저장할 데이터
	 * @param response 클라이언트에게 전달한 HTTP 응답 객체
	 */
	public void createSession(Object value, HttpServletResponse response) {
		// 세션 생성
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId, value);
		
	    long currentTime = System.currentTimeMillis();
	    sessionStore.put(sessionId + "_timestamp", currentTime); // 타임스탬프 저장
		
		// 쿠키 생성 후 추가
		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		cookie.setMaxAge(60 * 60); // 1시간동안 유효
		cookie.setHttpOnly(true);
		cookie.setSecure(true); // HTTPS 연결에서만 전송
		response.addCookie(cookie);
	}
	
	/**
	 * 세션 조회
	 * @param request HTTP 요청 객체
	 * @return 세션에 저장된 데이터
	 */
	public Object getSession(HttpServletRequest request) {
		Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
		if(cookie == null) { // 쿠키 없으면
			return null;
		}
		
	    String sessionId = cookie.getValue();
	    Long timestamp = (Long) sessionStore.get(sessionId + "_timestamp");
	    if (timestamp != null && System.currentTimeMillis() - timestamp > 60 * 60 * 1000) { // 1시간 초과
	        expire(request); // 세션 만료
	        return null;
	    }
		
		return sessionStore.get(cookie.getValue());
	}
	
	/**
	 * 세션 삭제
	 * @param request HTTP 요청 객체
	 */
	public void expire(HttpServletRequest request) {
		Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
		if(cookie != null) { // 쿠키 존재하면
			cookie.setMaxAge(0); // 쿠키 만료
//			cookie.setPath("/"); // 쿠키 경로 설정(기본값 /)
			sessionStore.remove(cookie.getValue()); // 삭제
		}
		sessionStore.clear(); // 세션 저장소 비우기
	}
	
	/**
	 * 쿠키 찾기
	 * @param request HTTP 요청 객체
	 * @param cookieName 쿠키 이름
	 * @return 찾은 쿠키, 없으면 null
	 */
	public Cookie findCookie(HttpServletRequest request, String cookieName) {
		if(request.getCookies() == null) { // 없으면 null
			return null;
		}
		
		return Arrays.stream(request.getCookies()) // 있으면 반환
				.filter(c -> c.getName().equals(cookieName))
				.findAny()
				.orElse(null);
	}
}
