package com.example.libraryapp.repository;
import com.example.libraryapp.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BooksRepository extends PagingAndSortingRepository<Book, String> {


    Page<Book> findAll(Pageable pageable);
}
