package com.project.fitness.service;

import com.project.fitness.DTO.RecommendationResponse;
import com.project.fitness.DTO.UserRegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RecommendationRepository recommendationRepository;

    public UserResponse register(UserRegisterRequest userRegisterRequest) {

        User user = User.builder()
                .email(userRegisterRequest.getEmail())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .password(userRegisterRequest.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User savedUser) {
        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        return response;
    }

    public List<UserResponse> getAllUsers() {
       List<User> fetchedUser = userRepository.findAll();

        return fetchedUser.stream()
                .map(this::mapToResponse)
                .toList();
    }
}
