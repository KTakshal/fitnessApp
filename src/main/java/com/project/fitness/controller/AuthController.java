package com.project.fitness.controller;

import com.project.fitness.DTO.RegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor //it will create constructor for all final fields
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest registerRequest){

        return userService.register(registerRequest);
    }

    @GetMapping("/get")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
