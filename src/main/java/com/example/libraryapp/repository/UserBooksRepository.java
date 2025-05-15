package com.example.libraryapp.repository;

import com.example.libraryapp.model.UserBooks;
import org.springframework.data.repository.CrudRepository;

public interface UserBooksRepository extends CrudRepository<UserBooks, Long> {

    
}
