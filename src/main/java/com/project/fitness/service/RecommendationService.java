package com.project.fitness.service;

import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.DTO.RecommendationResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    public RecommendationResponse genearteRecommendation(RecommendationRequest recommendationRequest) {
        User user = userRepository.findById(recommendationRequest.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"+recommendationRequest.getUserId()));

        Activity activity = activityRepository.findById(recommendationRequest.getActivityId())
                .orElseThrow(()->new RuntimeException("User not found"+recommendationRequest.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(recommendationRequest.getImprovements())
                .suggestions(recommendationRequest.getSuggestions())
                .safety(recommendationRequest.getSafety())
                .build();

        Recommendation savedRecommendation = recommendationRepository.save(recommendation);

        return  mapToRecommendationResponse(savedRecommendation);

    }

    public RecommendationResponse mapToRecommendationResponse(Recommendation savedRecommendation) {
        return RecommendationResponse.builder()
                .userId(savedRecommendation.getUser().getId())
                .activityId(savedRecommendation.getActivity().getId())
                .improvements(savedRecommendation.getImprovements())
                .suggestions(savedRecommendation.getSuggestions())
                .safety(savedRecommendation.getSuggestions())
                .build();
    }

    public List<RecommendationResponse> getUserRecommendation(String id) {
        List<Recommendation> recommendation = recommendationRepository.findByUserId(id);

        return recommendation.stream()
                .map(this::mapToRecommendationResponse)
                .toList();

    }

    public  List<RecommendationResponse> getActivityRecommendation(String id) {
        List<Recommendation> recommendation = recommendationRepository.findByActivityId(id);
        return recommendation.stream()
                .map(this::mapToRecommendationResponse)
                .toList();
    }
}
