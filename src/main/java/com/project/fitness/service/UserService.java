package com.project.fitness.service;

import com.project.fitness.DTO.RegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {
        /*User user = new User(
                null,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getCreatedAt(),
                registerRequest.getUpdatedAt(),
                List.of(),
                List.of()
                ); */

        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User savedUser) {
        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
