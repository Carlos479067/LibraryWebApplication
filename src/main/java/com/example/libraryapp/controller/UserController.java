package com.example.libraryapp.controller;
import com.example.libraryapp.dto.UserDto;
import com.example.libraryapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
