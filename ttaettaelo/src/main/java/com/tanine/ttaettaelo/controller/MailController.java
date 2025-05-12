package com.tanine.ttaettaelo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;

	// 인증 이메일 전송
	@PostMapping("/mailSend")
    public ResponseEntity<?> mailSend(@RequestParam(name = "email") String email) {
        try {
            mailService.sendMail(email);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

	// 인증번호 일치여부 확인
    @GetMapping("/mailCheck")
    public ResponseEntity<?> mailCheck(@RequestParam(name = "email") String mail, @RequestParam(name = "userNumber") String userNumber) {

    	boolean isMatch = mailService.checkNumber(mail, userNumber);
        return ResponseEntity.ok(Map.of("match", isMatch));
    }
}
