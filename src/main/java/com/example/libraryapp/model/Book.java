package com.example.libraryapp.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
public class Book {

    @Id
    private String isbn;
    @ElementCollection
    private List<String> authors;
    private String title;
    private String genre;
    private String description;
    private String publishDate;
    private String thumbNail;
    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<UserBooks> userBooks;

    public Book() {

    }

    public Book(String isbn, String title, List<String> authors, String genre,
                String description, String publishDate, String thumbNail) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.description = description;
        this.publishDate = publishDate;
        this.thumbNail = thumbNail;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<UserBooks> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<UserBooks> userBooks) {
        this.userBooks = userBooks;
    }
}
