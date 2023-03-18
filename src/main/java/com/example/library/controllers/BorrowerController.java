package com.example.library.controllers;


import com.example.library.Models.Borrower;
import com.example.library.Repositories.BookRepository;
import com.example.library.Repositories.BorrowerRepository;
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



    public BorrowerController(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }


    @GetMapping("/borrower/")
    public String borrowerPage(Model model){
        model.addAttribute("borrower", borrowerRepository.findAll());

        return "borrowerPage";
    }

    @GetMapping("/borrower/edit/{id}")
    public String borrowerEdit(@PathVariable Long id, Model model){
        model.addAttribute("borrower", borrowerRepository.findAllById(id));
        return "borrowerEdit";
    }
    @GetMapping("/borrower/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        borrowerRepository.deleteById(id);

        return "redirect:/borrower/";
    }
    @PostMapping("/borrower/edit/{id}")
    public String edit(@Valid @ModelAttribute("book") Borrower book, BindingResult errors , Model model){
        if (errors.hasErrors() || book.getName().isEmpty()
                || book.getEmail().isEmpty() || book.getPhone().isEmpty()) {
            return "redirect:/edit/"+book.getId();
        }

        borrowerRepository.save(book);
        return "redirect:/borrower/";
    }
    @PostMapping("/borrower/")
    public String postMain(@Valid @ModelAttribute("book") Borrower book, BindingResult errors , Model model){
        if (errors.hasErrors() || book.getName().isEmpty()
                || book.getEmail().isEmpty() || book.getPhone().isEmpty()) {
            return "redirect:/borrower/";
        }

        borrowerRepository.save(book);




        return "redirect:/borrower/";
    }
}
