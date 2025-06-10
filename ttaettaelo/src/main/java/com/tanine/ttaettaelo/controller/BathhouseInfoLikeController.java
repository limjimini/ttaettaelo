package com.tanine.ttaettaelo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanine.ttaettaelo.dto.BathhouseInfoLikeDTO;
import com.tanine.ttaettaelo.service.BathhouseInfoLikeService;

import lombok.RequiredArgsConstructor;

/**
 * 목욕탕 좋아요 관련 요청을 처리하는 컨트롤러 서비스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bathhouse")
public class BathhouseInfoLikeController {

	private final BathhouseInfoLikeService bathhouseInfoLikeService;
	
	/**
	 * 목욕탕 정보글에 좋아요 추가 또는 삭제
	 * @param bathhouseInfoLikeDto 좋아요 기능을 실행할 목욕탕 정보
	 * @return 좋아요 상태와 좋아요 개수가 포함된 Map
	 */
    @PostMapping("/like")
    public ResponseEntity<Map<String, Object>> bathhouseLike(@RequestBody BathhouseInfoLikeDTO bathhouseInfoLikeDto) {
        boolean like = bathhouseInfoLikeService.bathhouseLike(bathhouseInfoLikeDto.getBathhouseInfoId(), bathhouseInfoLikeDto.getMemberId()); // 좋아요 추가/삭제
        int likeCount = bathhouseInfoLikeService.getLikeCount(bathhouseInfoLikeDto.getBathhouseInfoId()); // 좋아요 개수
        
        Map<String, Object> result = new HashMap<>();
        result.put("like", like);
        result.put("likeCount", likeCount);
        return ResponseEntity.ok(result);
    }

    /**
     * 목욕탕 정보글의 좋아요 총 개수
     * @param bathhouseInfoId 좋아요 개수를 조회할 목욕탕 정보글 번호
     * @return 좋아요 개수를 포함한 Map
     */
    @GetMapping("/{bathhouseInfoId}/like")
    public ResponseEntity<Map<String, Integer>> getLikeCount(@PathVariable("bathhouseInfoId") Long bathhouseInfoId) {
        int likeCount = bathhouseInfoLikeService.getLikeCount(bathhouseInfoId); // 좋아요 개수
        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }
}
