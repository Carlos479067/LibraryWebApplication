package com.example.libraryapp.service;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.repository.BooksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        Page<Book> booksPage = booksRepository.findAll(PageRequest.of(0, 10));
        return booksPage;
    }
}
