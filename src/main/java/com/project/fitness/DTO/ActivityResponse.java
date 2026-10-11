package com.project.fitness.DTO;

import com.project.fitness.model.ActivityType;
import com.project.fitness.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponse {
    private String id;
    private UserResponse user;
    private ActivityType activityType;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime createdAt;

    private List<Recommendation> recommendations = new ArrayList<>();

}
