package com.example.libraryapp.controller;
import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.service.BooksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {

        this.booksService = booksService;
    }

    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/books/search")
    public List<BookDto> searchBooks(@RequestParam String query) {
        return booksService.searchBooks(query);
    }

}
