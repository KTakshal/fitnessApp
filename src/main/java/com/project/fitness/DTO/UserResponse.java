package com.project.fitness.DTO;

import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String email;
    //private String password; //not good practice to show password in response hence commenting
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

}
