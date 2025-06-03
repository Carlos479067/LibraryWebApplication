package com.example.libraryapp.controller;
import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.enums.BookStatus;
import com.example.libraryapp.model.UserBooks;
import com.example.libraryapp.service.UserBookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserBookController {

    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {

        this.userBookService = userBookService;
    }

    @PostMapping("/users/{userId}/books")
    public UserBooks addBookToCollection(@RequestBody BookDto request, @PathVariable Long userId, @RequestParam BookStatus bookStatus) {
         return userBookService.addBookToCollection(userId, request, bookStatus);
    }

    @GetMapping("/users/{userId}/books")
    public List<BookDto> returnUserCollection(@PathVariable Long userId, @RequestParam BookStatus bookStatus) {
        return userBookService.returnUserCollection(userId, bookStatus);
    }
}
