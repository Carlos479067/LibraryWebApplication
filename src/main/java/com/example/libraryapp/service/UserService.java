package com.example.libraryapp.service;
import com.example.libraryapp.dto.UserDto;
import com.example.libraryapp.enums.BookStatus;
import com.example.libraryapp.model.User;
import com.example.libraryapp.repository.UserBooksRepository;
import com.example.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserBooksRepository userBooksRepository;

    public UserService(UserRepository userRepository, UserBooksRepository userBooksRepository) {
        this.userRepository = userRepository;
        this.userBooksRepository = userBooksRepository;
    }

    public UserDto getAccountInformation(Long userId) {


        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found."));
        int ownedCount = userBooksRepository.countByBookStatusAndUser_Id(BookStatus.OWNED, userId);
        int wishListCount = userBooksRepository.countByBookStatusAndUser_Id(BookStatus.WISHLIST, userId);

        return mapToUser(user, ownedCount, wishListCount);
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
}
