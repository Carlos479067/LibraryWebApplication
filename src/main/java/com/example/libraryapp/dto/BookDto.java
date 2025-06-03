package com.example.libraryapp.dto;
import java.util.List;

public class BookDto {

    private String isbn;
    private String title;
    private String description;
    private String genre;
    private String thumbNail;
    private String publishDate;
    private List<String> authors;

    public BookDto() {

    }

    public BookDto(String isbn, String title, String description, String genre, String thumbNail, String publishDate, List<String> authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.thumbNail = thumbNail;
        this.authors = authors;
        this.publishDate = publishDate;
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getThumbnail() {
        return thumbNail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbNail = thumbnail;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
