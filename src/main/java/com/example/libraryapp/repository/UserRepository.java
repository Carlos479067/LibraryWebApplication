package com.example.libraryapp.repository;
import com.example.libraryapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
