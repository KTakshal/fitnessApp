package com.project.fitness.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @CreatedDate
    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false )
    private LocalDateTime updatedAt;
}
