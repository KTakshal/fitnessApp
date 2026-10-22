package com.project.fitness.service;

import com.project.fitness.DTO.LoginRequest;
import com.project.fitness.DTO.UserRegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        UserRole role = userRegisterRequest.getRole() !=null ? userRegisterRequest.getRole() :
                UserRole.USER;
        User user = User.builder()
                .email(userRegisterRequest.getEmail())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public UserResponse mapToResponse(User savedUser) {
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

    public User authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user == null)
            throw  new RuntimeException("Invalid Credintials");

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())) {
            throw  new RuntimeException("Invalid Credintials");
        }
        return user;
    }
}
