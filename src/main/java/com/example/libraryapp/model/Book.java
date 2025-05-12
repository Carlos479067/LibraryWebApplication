package com.example.libraryapp.model;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Book {

    @Id
    private String isbn;
    @ElementCollection
    private List<String> authors;
    private String genre;
    private String description;
    private String publishDate;
    private String thumbNail;

    public Book() {

    }

    public Book(String isbn, List<String> authors, String genre,
                String description, String publishDate, String thumbNail) {
        this.isbn = isbn;
        this.authors = authors;
        this.genre = genre;
        this.description = description;
        this.publishDate = publishDate;
        this.thumbNail = thumbNail;
    }

    public String getIsbn() {
        return isbn;
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
}
