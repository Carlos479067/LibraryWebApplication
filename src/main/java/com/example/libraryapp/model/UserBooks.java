package com.example.libraryapp.model;
import com.example.libraryapp.enums.BookStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class UserBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userBook_id;
    @ManyToOne
    @JoinColumn(name = "bookId")
    @JsonBackReference
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    private BookStatus bookStatus;

    public UserBooks() {

    }

    public UserBooks(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public UserBooks(Book book, User user, BookStatus bookStatus) {
        this.book = book;
        this.user = user;
        this.bookStatus = bookStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookStatus getStatus() {
        return bookStatus;
    }

    public void setStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public long getUserBook_id() {
        return userBook_id;
    }

    public void setUserBook_id(long userBook_id) {
        this.userBook_id = userBook_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
