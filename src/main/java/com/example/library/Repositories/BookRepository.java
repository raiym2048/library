package com.example.library.Repositories;

import com.example.library.Models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findAllById(Long id);

    Boolean existsByAuthor(String author);
    public default Book updateBook(Book book){
        Book book1 = book;
        return book1;
    }


}
