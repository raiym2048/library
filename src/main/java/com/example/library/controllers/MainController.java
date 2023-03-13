package com.example.library.controllers;


import com.example.library.Models.Book;
import com.example.library.Models.Borrower;
import com.example.library.Repositories.BookRepository;
import com.example.library.Repositories.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public MainController(BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @GetMapping("/")
    public String maninPage(){
        Book book = new Book();
        book.setAuthor("author1");
        book.setTitle("tittle1");
        book.setISBN("ISBN1");
        book.setPublisher("Publisher1");
        bookRepository.save(book);

        Borrower borrower = new Borrower();
        borrower.setEmail("email1");
        borrower.setName("name1");
        borrower.setPhone_number("phone_number1");

        borrowerRepository.save(borrower);

        return "mainPage";
    }



}
