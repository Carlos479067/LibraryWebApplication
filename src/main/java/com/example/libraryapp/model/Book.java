package com.example.libraryapp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    private String isbn;


}
