package com.project.fitness.controller;

import com.project.fitness.DTO.LoginRequest;
import com.project.fitness.DTO.LoginResponse;
import com.project.fitness.DTO.UserRegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.security.JwtUtils;
import com.project.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor //it will create constructor for all final fields
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest userRegisterRequest){

        return ResponseEntity.ok(userService.register(userRegisterRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        try {
            User user = userService.authenticate(loginRequest);
            String token = jwtUtils.generateToken(user.getId(),user.getRole().name());

            return ResponseEntity.ok(new LoginResponse(
                    token,userService.mapToResponse(user)
            ));
            }
            catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }

}
