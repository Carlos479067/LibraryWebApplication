package com.example.libraryapp.service;
import com.example.libraryapp.dto.AddUserBookRequest;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBooks;
import com.example.libraryapp.repository.BooksRepository;
import com.example.libraryapp.repository.UserBooksRepository;
import com.example.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBookService {

    private final UserBooksRepository userBooksRepository;
    private final BooksRepository booksRepository;
    private final UserRepository userRepository;

    public UserBookService(UserBooksRepository userBooksRepository, BooksRepository booksRepository, UserRepository userRepository) {
        this.userBooksRepository = userBooksRepository;
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
    }

    public UserBooks addBookToCollection(AddUserBookRequest request) {

        String bookId =  request.getBookId();
        Long userId = request.getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + request.getUserId() + " not found"));
        Book book = booksRepository.findByIsbn(bookId).orElseThrow(() -> new RuntimeException("Book " + request.getBookId() +  " not found"));
        UserBooks userBooks = new UserBooks(book, user);
        return userBooksRepository.save(userBooks);
    }

}
