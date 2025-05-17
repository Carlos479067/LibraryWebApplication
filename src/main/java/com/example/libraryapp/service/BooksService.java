package com.example.libraryapp.service;
import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.repository.BooksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {

        this.booksRepository = booksRepository;
    }

    public Page<BookDto> getAllBooks(Pageable pageable) {

        Page<Book> book = booksRepository.findAll(pageable);

        return book.map(this::mapToBook);
    }

    private BookDto mapToBook(Book book) {
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
