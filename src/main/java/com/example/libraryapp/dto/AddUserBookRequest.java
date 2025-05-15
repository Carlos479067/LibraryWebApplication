package com.example.libraryapp.dto;

public class AddUserBookRequest {

    String bookId;
    Long userId;

    public AddUserBookRequest() {

    }

    public AddUserBookRequest(String bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
