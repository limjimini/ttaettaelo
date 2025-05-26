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

import com.tanine.ttaettaelo.dto.BathhouseInfoDTO;
import com.tanine.ttaettaelo.dto.BathhouseInfoLikeDTO;
import com.tanine.ttaettaelo.service.BathhouseInfoLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bathhouse")
public class BathhouseInfoLikeController {

	private final BathhouseInfoLikeService bathhouseInfoLikeService;
	
    @PostMapping("/like")
    public ResponseEntity<Map<String, Object>> bathhouseLike(@RequestBody BathhouseInfoLikeDTO bathhouseInfoLikeDto) { // 좋아요 실행/취소
        boolean like = bathhouseInfoLikeService.bathhouseLike(bathhouseInfoLikeDto.getBathhouseInfoId(), bathhouseInfoLikeDto.getMemberId());
        int likeCount = bathhouseInfoLikeService.getLikeCount(bathhouseInfoLikeDto.getBathhouseInfoId());
        Map<String, Object> response = new HashMap<>();
        response.put("like", like);
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bathhouseInfoId}/like")
    public ResponseEntity<Map<String, Integer>> getLikeCount(@PathVariable("bathhouseInfoId") Long bathhouseInfoId) { // 좋아요 개수
        int likeCount = bathhouseInfoLikeService.getLikeCount(bathhouseInfoId);
        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }
}
