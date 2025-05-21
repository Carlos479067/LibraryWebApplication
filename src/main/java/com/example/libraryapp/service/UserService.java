package com.example.libraryapp.service;
import com.example.libraryapp.dto.UserDto;
import com.example.libraryapp.model.User;
import com.example.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getAccountInformation(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found."));

        return mapToUser(user);
    }

    private UserDto mapToUser(User user) {

        UserDto userDto = new UserDto();

        userDto.setId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;

    }
}
