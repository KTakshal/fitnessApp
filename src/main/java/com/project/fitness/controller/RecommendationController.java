package com.project.fitness.controller;

import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.DTO.RecommendationResponse;
import com.project.fitness.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation
            (@RequestBody RecommendationRequest request)
    {
        RecommendationResponse recommendation= recommendationService.genearteRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/user")
    public ResponseEntity<List<RecommendationResponse>> getUserRecommendation(@RequestHeader("X-User-Id") String id){
        return ResponseEntity.ok(recommendationService.getUserRecommendation(id));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<RecommendationResponse>> getActivityRecommendation(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}
