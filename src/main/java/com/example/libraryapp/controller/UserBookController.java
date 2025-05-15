package com.example.libraryapp.controller;
import com.example.libraryapp.dto.AddUserBookRequest;
import com.example.libraryapp.model.UserBooks;
import com.example.libraryapp.service.UserBookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserBookController {

    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @PostMapping("/userCollection")
    public UserBooks addBookToCollection(@RequestBody AddUserBookRequest request) {
         return userBookService.addBookToCollection(request);
    }
}
