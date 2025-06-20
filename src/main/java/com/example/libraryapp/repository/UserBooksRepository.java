package com.example.libraryapp.repository;
import com.example.libraryapp.enums.BookStatus;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.UserBooks;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import static com.example.libraryapp.enums.BookStatus.OWNED;

public interface UserBooksRepository extends CrudRepository<UserBooks, Long> {

    List<UserBooks> findByUser_Id(long userId);

    Optional<UserBooks> findByBookStatusAndBookAndUser(BookStatus bookStatus, Book book, User user);

    List<UserBooks> findByBookStatusAndUser_Id(BookStatus bookStatus, long userId);

    Optional<UserBooks> findByUser_IdAndBook_IsbnAndBookStatus(long userId, String isbn, BookStatus bookStatus);

    int countByBookStatusAndUser_Id(BookStatus bookStatus, long userId);

}
