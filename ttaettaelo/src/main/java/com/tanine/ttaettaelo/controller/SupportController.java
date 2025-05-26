package com.tanine.ttaettaelo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.SupportAnswerDTO;
import com.tanine.ttaettaelo.dto.SupportDTO;
import com.tanine.ttaettaelo.service.MemberService;
import com.tanine.ttaettaelo.service.SupportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SupportController {

	private final SupportService supportService;
	private final MemberService memberService;

	// 전체 문의글 + 답변 리스트 조회
	@GetMapping("/support")
	public List<SupportDTO> getAllSupports() {
	    return supportService.getAllSupportWithAnswer();
	}
	
//	// 단일 문의글 조회
//	@GetMapping("/{id}")
//	public SupportDTO getSupport(@PathVariable Long id) {
//	    return supportService.getSupport(id);
//	}

	// 문의 등록
	@PostMapping("/support/submit")
	public ResponseEntity<String> submitSupport(@RequestBody SupportDTO supportDto) {
        try {
            // 지원글에 작성자 정보 추가 (member_id로 이름 가져오기)
//            Long memberId = supportDto.getMemberId();

            // SupportService에서 지원글 저장
            supportService.submitSupport(supportDto);
            return ResponseEntity.ok("문의글 작성 성공");
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의글 작성 실패");
        }
	}

//	// 답변 등록 (관리자)
//	@PostMapping("/{id}/answer")
//	public ResponseEntity<Void> writeAnswer(@PathVariable Long id, @RequestBody SupportAnswerDTO supportAnswerDto) {
//	    answerDto.setSupportId(id);
//	    supportService.writeAnswer(answerDto)
//	    return ResponseEntity.ok().build();
//	}
}
