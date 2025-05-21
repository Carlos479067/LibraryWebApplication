package com.example.libraryapp.service;
import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.enums.BookStatus;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBooks;
import com.example.libraryapp.repository.BooksRepository;
import com.example.libraryapp.repository.UserBooksRepository;
import com.example.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    public UserBooks addBookToCollection(Long userId, BookDto request, BookStatus bookStatus) {

        String bookId =  request.getIsbn();

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found"));
        Book book = booksRepository.findByIsbn(bookId).orElseThrow(() -> new RuntimeException("Book " + request.getIsbn() +  " not found"));

        if(userBooksRepository.findByBookStatusAndBookAndUser(bookStatus, book, user).isPresent()) {
            throw new RuntimeException("Book already added to " + bookStatus);
        }

        UserBooks userBooks = new UserBooks(book, user, bookStatus);
        return userBooksRepository.save(userBooks);
    }

    public List<BookDto> returnUserCollection(Long userId, BookStatus bookStatus) {

        List<UserBooks> userBooks = userBooksRepository.findByBookStatusAndUser_Id(bookStatus, userId);

        return userBooks.stream().map(this::mapToBook).collect(Collectors.toList());

    }

    private BookDto mapToBook(UserBooks userBooks) {
        Book book = userBooks.getBook();
        BookDto dto = new BookDto();

        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setThumbnail(book.getThumbNail());
        dto.setDescription(book.getDescription());
        dto.setGenre(book.getGenre());
        dto.setAuthors(book.getAuthors());

        return dto;
    }

}
