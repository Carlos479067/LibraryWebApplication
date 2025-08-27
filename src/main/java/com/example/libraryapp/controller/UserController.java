package com.example.libraryapp.controller;

import com.example.libraryapp.dto.SignUpDto;
import com.example.libraryapp.dto.UserDto;
import com.example.libraryapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/account")
    public UserDto getAccountInformation(@PathVariable Long userId) {
        return userService.getAccountInformation(userId);
    }

    @PostMapping("/signup")
    // Tells Spring to map the incoming JSON to your DTO
    public SignUpDto createUserAccount(@RequestBody SignUpDto signUpDto) {
        return userService.createUser(signUpDto);
    }
}
