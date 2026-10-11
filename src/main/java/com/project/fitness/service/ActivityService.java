package com.project.fitness.service;

import com.project.fitness.DTO.ActivityRequest;
import com.project.fitness.DTO.ActivityResponse;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityResponse createActivity(ActivityRequest activityRequest) {
        String user_Id = activityRequest.getUser_Id();
        User user = userRepository.findById(user_Id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + user_Id));

        Activity activity = Activity.builder()
                .user(user)
                .activityType(activityRequest.getActivityType())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .duration(activityRequest.getDuration())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();

        activity.setRecommendations(new ArrayList<>());

        if(activityRequest.getRecommendations()!=null)
        {
            for(Recommendation recommendation:activityRequest.getRecommendations()){
                recommendation.setActivity(activity);
                recommendation.setUser(user);
                activity.getRecommendations().add(recommendation);
            }
        }


       Activity savedActivity = activityRepository.save(activity);

        return mapToActivityResponse(savedActivity);

    }

    private ActivityResponse mapToActivityResponse(Activity activity) {

        return ActivityResponse.builder()
                .id(activity.getId())
                .activityType(activity.getActivityType())
                .additionalMetrics(activity.getAdditionalMetrics())
                .recommendations(activity.getRecommendations())
                .createdAt(activity.getCreatedAt())
                .caloriesBurned(activity.getCaloriesBurned())
                .duration(activity.getDuration())
                .additionalMetrics(activity.getAdditionalMetrics())
                .user(mapUserResponse(activity))
                .build();
    }

    private UserResponse mapUserResponse(Activity activity) {
        if(activity.getUser()!=null){
            return UserResponse.builder()
                    .id(activity.getUser().getId())
                    .email(activity.getUser().getEmail())
                    .firstName(activity.getUser().getFirstName())
                    .lastName(activity.getUser().getLastName())
                    .createdAt(activity.getUser().getCreatedAt())
                    .build();
        }
        return null;
    }

    public List<ActivityResponse> getAllActivities() {
        List<Activity> activity = activityRepository.findAll();
        return activity.stream()
                .map(this::mapToActivityResponse)
                .toList();
    }

    public List<ActivityResponse> getActivitesByUserId(String id) {
         List<Activity> activities = activityRepository.findAllByUserId(id);
         return activities.stream()
                 .map(this::mapToActivityResponse)
                 .collect(Collectors.toList());
    }

}
