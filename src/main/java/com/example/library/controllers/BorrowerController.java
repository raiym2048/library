package com.example.library.controllers;


import com.example.library.Models.Borrower;
import com.example.library.Repositories.BookRepository;
import com.example.library.Repositories.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BorrowerController {

    private final
    BorrowerRepository borrowerRepository;

    private final
    BookRepository bookRepository;



    public BorrowerController(BorrowerRepository borrowerRepository, BookRepository bookRepository) {
        this.borrowerRepository = borrowerRepository;
        this.bookRepository = bookRepository;
    }


    @GetMapping("/borrower/")
    public String borrowerPage(Model model){
        model.addAttribute("borrower", borrowerRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());

        return "borrowerPage";
    }

    @GetMapping("/borrower/edit/{id}")
    public String borrowerEdit(@PathVariable Long id, Model model){
        model.addAttribute("borrower", borrowerRepository.findAllById(id));
        model.addAttribute("books", bookRepository.findAll());

        return "borrowerEdit";
    }
    @GetMapping("/borrower/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        borrowerRepository.deleteById(id);

        return "redirect:/borrower/";
    }
    @PostMapping("/borrower/edit/{id}")
    public String edit(@Valid @ModelAttribute("borrower") Borrower borrower, BindingResult errors , Model model){
        if (errors.hasErrors() || borrower.getName().isEmpty()
                || borrower.getEmail().isEmpty() || borrower.getPhone().isEmpty()) {
            return "redirect:/edit/"+borrower.getId();
        }

        borrowerRepository.save(borrower);
        return "redirect:/borrower/";
    }
    @PostMapping("/borrower/")
    public String postMain(@Valid @ModelAttribute("borrower") Borrower borrower, BindingResult errors , Model model){
        if (errors.hasErrors() || borrower.getName().isEmpty()
                || borrower.getEmail().isEmpty() || borrower.getPhone().isEmpty()) {
            return "redirect:/borrower/";
        }

        borrowerRepository.save(borrower);




        return "redirect:/borrower/";
    }
}
