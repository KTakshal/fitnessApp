package com.project.fitness.DTO;

import com.project.fitness.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
