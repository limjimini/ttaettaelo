package com.tanine.ttaettaelo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * @param pageNumber 조회할 페이지 번호
	 * @param pageSize 한 페이지에 나타낼 문의글 수
	 * @return 문의글과 문의 답변 리스트
	 */
	@GetMapping("/support")
	public ResponseEntity<Map<String, Object>> getAllSupports(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "pageSize") int pageSize) {
	    List<SupportDTO> supports = supportService.getAllSupportWithAnswer(pageNumber, pageSize); // 문의글과 답변 가져오기
	    
	    int totalCount = supportService.getTotalSupportCount(); // 전체 문의글 수
	    int totalPages = (int) Math.ceil((double) totalCount / pageSize); // 전체 페이지 수
	    
	    // 결과 담기
	    Map<String, Object> result = new HashMap<>();
	    result.put("supports", supports);
	    result.put("currentPage", pageNumber);
	    result.put("totalPages", totalPages);
	    
	    return ResponseEntity.ok(result);
	    
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
