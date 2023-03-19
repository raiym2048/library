package com.example.library.Repositories;

import com.example.library.Models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findAllById(Long id);

    Book findAllByTitle(String title);
    @Query(value = "select * from book s where s.title like %:keyword% or s.author like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);




}
