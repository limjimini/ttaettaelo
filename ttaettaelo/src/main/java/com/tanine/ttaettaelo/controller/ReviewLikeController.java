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

import com.tanine.ttaettaelo.dto.ReviewLikeDTO;
import com.tanine.ttaettaelo.service.BathhouseInfoLikeService;
import com.tanine.ttaettaelo.service.ReviewLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewLikeController {

    private final ReviewLikeService reviewLikeService;

    @PostMapping("/like")
    public ResponseEntity<Map<String, Object>> reviewLike(@RequestBody ReviewLikeDTO reviewLikeDto) {
        boolean like = reviewLikeService.reviewLike(reviewLikeDto.getReviewId(), reviewLikeDto.getMemberId());
        int likeCount = reviewLikeService.getLikeCount(reviewLikeDto.getReviewId());

        Map<String, Object> result = new HashMap<>();
        result.put("like", like);
        result.put("likeCount", likeCount);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{reviewId}/like")
    public ResponseEntity<Map<String, Integer>> getLikeCount(@PathVariable("reviewId") long reviewId) {
        int likeCount = reviewLikeService.getLikeCount(reviewId);
        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }
}
