package com.example.libraryapp.repository;
import com.example.libraryapp.model.UserBooks;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserBooksRepository extends CrudRepository<UserBooks, Long> {

    List<UserBooks> findByUser_Id(long userId);

}
