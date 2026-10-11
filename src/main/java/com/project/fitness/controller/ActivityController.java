package com.project.fitness.controller;

import com.project.fitness.DTO.ActivityRequest;
import com.project.fitness.DTO.ActivityResponse;
import com.project.fitness.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest activityRequest)
    {
        return ResponseEntity.ok(activityService.createActivity(activityRequest));
    }

    @GetMapping("/user")
    public ResponseEntity<List<ActivityResponse>> getActivitesByUserId(@RequestHeader("X-User-Id") String id){
        return ResponseEntity.ok(activityService.getActivitesByUserId(id));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAllActivites(){
        return ResponseEntity.ok(activityService.getAllActivities());
    }
}
