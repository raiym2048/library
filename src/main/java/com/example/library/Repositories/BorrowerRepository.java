package com.example.library.Repositories;

import com.example.library.Models.Book;
import com.example.library.Models.Borrower;
import org.springframework.data.repository.CrudRepository;

public interface BorrowerRepository  extends CrudRepository<Borrower, Long> {

    Borrower findAllById(Long id);


}
