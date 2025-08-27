package com.example.libraryapp.repository;
import com.example.libraryapp.dto.UserDto;
import com.example.libraryapp.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);

}
