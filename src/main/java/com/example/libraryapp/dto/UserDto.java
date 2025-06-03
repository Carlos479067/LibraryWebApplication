package com.example.libraryapp.dto;

public class UserDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int ownedBooks;
    private int wishListBooks;

    public UserDto() {

    }

    public UserDto(String firstName, String lastName, String email, int ownedBooks, int wishListBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ownedBooks = ownedBooks;
        this.wishListBooks = wishListBooks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getOwnedBooks() {
        return ownedBooks;
    }

    public void setOwnedBooks(int ownedBooks) {
        this.ownedBooks = ownedBooks;
    }

    public int getWishListBooks() {
        return wishListBooks;
    }

    public void setWishListBooks(int wishListBooks) {
        this.wishListBooks = wishListBooks;
    }
}

