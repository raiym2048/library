package com.example.library.service;


import com.example.library.Models.Book;
import com.example.library.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        List<Book> list = (List<Book>) bookRepository.findAll();
        return list;
    }

    public List<Book> getByKeyword(String keyword){
        return bookRepository.findByKeyword(keyword);
    }
}
