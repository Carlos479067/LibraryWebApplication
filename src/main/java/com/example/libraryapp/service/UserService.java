package com.example.libraryapp.service;
import com.example.libraryapp.configuration.WebConfig;
import com.example.libraryapp.dto.SignUpDto;
import com.example.libraryapp.dto.UserDto;
import com.example.libraryapp.enums.BookStatus;
import com.example.libraryapp.model.User;
import com.example.libraryapp.repository.UserBooksRepository;
import com.example.libraryapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserBooksRepository userBooksRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, UserBooksRepository userBooksRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userBooksRepository = userBooksRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto getAccountInformation(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found."));
        int ownedCount = userBooksRepository.countByBookStatusAndUser_Id(BookStatus.OWNED, userId);
        int wishListCount = userBooksRepository.countByBookStatusAndUser_Id(BookStatus.WISHLIST, userId);

        return mapToUser(user, ownedCount, wishListCount);
    }

    public SignUpDto createUser(SignUpDto signUpDto) {

        User user = new User();

        if(userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exist");
        }

        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        String plainTextPassword = signUpDto.getPassword();
        String hashedPassword = bCryptPasswordEncoder.encode(plainTextPassword);
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);
        SignUpDto dto = mapToSignUpDto(savedUser);

        return dto;

    }

    private UserDto mapToUser(User user, int ownedCount, int wishCount) {

        UserDto userDto = new UserDto();

        userDto.setId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setOwnedBooks(ownedCount);
        userDto.setWishListBooks(wishCount);

        return userDto;

    }

    private SignUpDto mapToSignUpDto(User user) {
        SignUpDto signUpDto = new SignUpDto();

        signUpDto.setId(user.getUserId());
        signUpDto.setEmail(user.getEmail());
        signUpDto.setFirstName(user.getFirstName());
        signUpDto.setLastName(user.getLastName());
        signUpDto.setPassword(user.getPassword());

        return signUpDto;
    }
}
