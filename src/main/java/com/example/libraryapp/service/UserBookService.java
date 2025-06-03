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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserBookService {

    private final UserBooksRepository userBooksRepository;
    private final BooksRepository booksRepository;
    private final UserRepository userRepository;
    private final BooksService booksService;

    public UserBookService(UserBooksRepository userBooksRepository, BooksRepository booksRepository, UserRepository userRepository, BooksService booksService) {
        this.userBooksRepository = userBooksRepository;
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
        this.booksService = booksService;
    }

    public UserBooks addBookToCollection(Long userId, BookDto request, BookStatus bookStatus) {

        String bookId =  request.getIsbn();

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found"));

        if(userBooksRepository.findByUser_IdAndBook_IsbnAndBookStatus(userId, bookId, BookStatus.OWNED).isPresent()) {
            throw new RuntimeException("This book is already in your owned collection.");
        }
        if (userBooksRepository.findByUser_IdAndBook_IsbnAndBookStatus(userId, bookId, BookStatus.WISHLIST).isPresent()) {
            throw new RuntimeException("This book is already in your wishlist.");
        }

        Book book = booksRepository.findByIsbn(bookId).orElse(null);

        if(book == null) {
            Optional<BookDto> searchIsbn = booksService.searchIsbn(bookId);
            if(searchIsbn.isPresent()) {
                BookDto fetchedBook = searchIsbn.get();
                book = new Book();

                book.setIsbn(fetchedBook.getIsbn());
                book.setTitle(fetchedBook.getTitle());
                book.setThumbNail(fetchedBook.getThumbnail());
                book.setDescription(fetchedBook.getDescription());
                book.setAuthors(fetchedBook.getAuthors());
                book.setGenre(fetchedBook.getGenre());
                book.setPublishDate(fetchedBook.getPublishDate());

                booksRepository.save(book);
            }
            else
            {
                throw new RuntimeException("Book with ISBN " + bookId + " could not be found.");
            }
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
        dto.setPublishDate(book.getPublishDate());

        return dto;
    }

}
