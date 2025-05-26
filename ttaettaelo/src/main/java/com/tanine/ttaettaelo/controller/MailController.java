package com.tanine.ttaettaelo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.service.MailService;

import lombok.RequiredArgsConstructor;

/**
 * 이메일 인증 관련 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MailController {

	private final MailService mailService;

	/**
	 * 사용자가 입력한 이메일로 인증메일 전송
	 * @param email 사용자가 입력한 이메일 주소
	 * @return 인증메일 전송 성공 여부
	 */
	@PostMapping("/mailSend")
    public ResponseEntity<?> mailSend(@RequestParam(name = "email") String email) {
        try {
            mailService.sendMail(email); // 인증메일 전송
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

	/**
	 * 사용자가 입력한 인증번호가 일치하는지 확인
	 * @param email 사용자가 입력한/인증메일을 받은 이메일 주소
	 * @param memberNumber 사용자가 입력한 인증번호
	 * @return 인증번호 일치 여부
	 */
    @GetMapping("/mailCheck")
    public ResponseEntity<?> mailCheck(@RequestParam(name = "email") String email, @RequestParam(name = "memberNumber") String memberNumber) {
    	boolean isMatched = mailService.checkNumber(email, memberNumber); // 인증번호 확인
        return ResponseEntity.ok(Map.of("match", isMatched));
    }
}
