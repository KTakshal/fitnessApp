package com.project.fitness.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class RecommendationResponse {
    private String userId;
    private String activityId;

    private List<String> improvements;


    private List<String> suggestions;


    private List<String> safety;
}
