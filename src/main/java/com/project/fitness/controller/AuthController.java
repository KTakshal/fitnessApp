package com.project.fitness.controller;

import com.project.fitness.DTO.UserRegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor //it will create constructor for all final fields
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest userRegisterRequest){

        return ResponseEntity.ok(userService.register(userRegisterRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
