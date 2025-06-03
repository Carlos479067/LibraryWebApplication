package com.example.libraryapp.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int ownedBooks;
    private int wishListBooks;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserBooks> userBooks;

    public User() {

    }

    public User(String firstName, String lastName,
                String email, String password, List<UserBooks> userBooks, int ownedBooks, int wishListBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userBooks = userBooks;
        this.ownedBooks = ownedBooks;
        this.wishListBooks = wishListBooks;
    }

    public long getUserId() {
        return id;
    }

    public void setUserId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<UserBooks> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<UserBooks> userBooks) {
        this.userBooks = userBooks;
    }

    public int getWishListBooks() {
        return wishListBooks;
    }

    public void setWishListBooks(int wishListBooks) {
        this.wishListBooks = wishListBooks;
    }

    public int getOwnedBooks() {
        return ownedBooks;
    }

    public void setOwnedBooks(int ownedBooks) {
        this.ownedBooks = ownedBooks;
    }
}
