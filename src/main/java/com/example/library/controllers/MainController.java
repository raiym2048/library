package com.example.library.controllers;


import com.example.library.Models.Book;
import com.example.library.Models.Borrower;
import com.example.library.Repositories.BookRepository;
import com.example.library.Repositories.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class MainController {
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public MainController(BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @GetMapping("/")
    public String homePage(Model model){
        Iterable<Book> books = bookRepository.findAll();
/*        Book book1 = new Book();
        book1.setAuthor("asd");
        book1.setISBN("asd");
        book1.setPublisher("asd");
        book1.setTitle("asd");
        bookRepository.save(book1);*/






        model.addAttribute("book",books);

        return "mainPage";

    }
    @GetMapping("/edit/{id}")
    public String editB(Model model){
        return "main";
    }
    @PostMapping("/")
    public String postMain(@ModelAttribute("book") Book book,Model model){

        bookRepository.save(book);




        return "redirect:/";
    }



}
