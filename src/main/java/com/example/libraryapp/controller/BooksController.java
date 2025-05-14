package com.example.libraryapp.controller;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.service.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public Page<Book> getAllBooks(Pageable pageable) {
        return booksService.getAllBooks(pageable);
    }
}
