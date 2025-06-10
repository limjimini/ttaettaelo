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

/**
 * 리뷰 좋아요 관련 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewLikeController {

    private final ReviewLikeService reviewLikeService;

    /**
     * 리뷰에 좋아요 추가 또는 삭제
     * @param reviewLikeDto 좋아요 기능을 실행할 리뷰의 정보
     * @return 좋아요 상태와 좋아요 개수가 포함된 Map
     */
    @PostMapping("/like")
    public ResponseEntity<Map<String, Object>> reviewLike(@RequestBody ReviewLikeDTO reviewLikeDto) {
        boolean like = reviewLikeService.reviewLike(reviewLikeDto.getReviewId(), reviewLikeDto.getMemberId()); // 좋아요 추가/삭제
        int likeCount = reviewLikeService.getLikeCount(reviewLikeDto.getReviewId()); // 좋아요 개수

        Map<String, Object> result = new HashMap<>();
        result.put("like", like);
        result.put("likeCount", likeCount);
        return ResponseEntity.ok(result);
    }

    /**
     * 리뷰의 좋아요 총 개수
     * @param reviewId 좋아요 개수를 조회할 리뷰 번호
     * @return 좋아요 개수를 포함한 Map
     */
    @GetMapping("/{reviewId}/like")
    public ResponseEntity<Map<String, Integer>> getLikeCount(@PathVariable("reviewId") long reviewId) {
        int likeCount = reviewLikeService.getLikeCount(reviewId); // 좋아요 개수
        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }
}
