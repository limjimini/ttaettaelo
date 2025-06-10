package com.tanine.ttaettaelo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.SupportDTO;
import com.tanine.ttaettaelo.service.SupportService;

import lombok.RequiredArgsConstructor;

/**
 * 문의하기 관련 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SupportController {

	private final SupportService supportService;

	/**
	 * 문의글과 문의 답변 조회
	 * @return 문의글과 문의 답변 리스트
	 */
	@GetMapping("/support")
	public List<SupportDTO> getAllSupports() {
	    return supportService.getAllSupportWithAnswer();
	}

	/**
	 * 문의 등록
	 * @param supportDto 등록할 문의 정보
	 * @return 문의글 작성 성공 또는 실패 응답
	 */
	@PostMapping("/support/submit")
	public ResponseEntity<String> submitSupport(@RequestBody SupportDTO supportDto) {
        try {
            supportService.submitSupport(supportDto); // SupportService에서 문의글 저장
            return ResponseEntity.ok("문의글 작성 성공");
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의글 작성 실패");
        }
	}
}
