package com.example.library.controllers;


import com.example.library.Models.Book;
import com.example.library.Repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
public class MainController {

    private final BookRepository bookRepository;

    public MainController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("book",bookRepository.findAll());

        return "mainPage";

    }
    @GetMapping("/edit/{id}")
    public String editB(@PathVariable Long id, Model model){
        model.addAttribute("book", bookRepository.findAllById(id));
        return "edit";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        bookRepository.deleteById(id);

        return "redirect:/";
    }
    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("book") Book book, BindingResult errors , Model model){
        if (errors.hasErrors() || book.getTitle().isEmpty()
                || book.getAuthor().isEmpty() || book.getPublisher().isEmpty() ||
                book.getISBN().isEmpty()) {
            return "redirect:/edit/"+book.getId();
        }

        bookRepository.save(book);
        return "redirect:/";
    }
    @PostMapping("/")
    public String postMain(@Valid @ModelAttribute("book") Book book, BindingResult errors , Model model){
        if (errors.hasErrors() || book.getTitle().isEmpty()
                || book.getAuthor().isEmpty() || book.getPublisher().isEmpty() ||
                book.getISBN().isEmpty()) {
            return "redirect:/";
        }

        bookRepository.save(book);




        return "redirect:/";
    }




}
