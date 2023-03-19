package com.example.library.controllers;


import com.example.library.Models.Book;
import com.example.library.Models.Borrower;
import com.example.library.Repositories.BookRepository;
import com.example.library.Repositories.BorrowerRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BorrowerController {

    private final
    BorrowerRepository borrowerRepository;

    private final
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @RequestMapping(path = {"/borrower/search","/search"})
    public String home(Book shop, Model model, String keyword) {
        if(keyword!=null) {
            List<Book> list = bookService.getByKeyword(keyword);
            model.addAttribute("list", list);
        }else {
            List<Book> list = bookService.getAllBooks();
            model.addAttribute("list", list);}
        return "searchPage";
    }



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

    @GetMapping("/borrower/search/")
    public String postSearch(Model model){
        model.addAttribute("book", new Book());
        System.out.println(model.getAttribute("book"));


        return "searchPage";
    }
    @PostMapping("/borrower/search/{title}")
    public String se(@PathVariable("title") String title, Model model){
        model.addAttribute("book", bookRepository.findAllByTitle(title));
        System.out.println("\n\n\n\n"+title+"\n\n\n\n");
        return "searchPage";
    }
    @PostMapping("/borrower/")
    public String postMain(@Valid @ModelAttribute("borrower") Borrower borrower, BindingResult errors , Model model){
        if (errors.hasErrors() || borrower.getName().isEmpty()
                || borrower.getEmail().isEmpty() || borrower.getPhone().isEmpty() || borrower.getBorrowersBook().isEmpty()) {
            return "redirect:/borrower/";
        }

        borrowerRepository.save(borrower);




        return "redirect:/borrower/";
    }
}
