package hh.backend.bookstore.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    BookRepository bookRepository;
    CategoryRepository categoryRepository;

    BookController(BookRepository bookRepository, CategoryRepository categoryRepository){
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/bookstore")
    public String welcomeToTheSite(Model model){
        return "etusivu"; //etusivu.html
    }

    @GetMapping("/booklist")
    public String listAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist"; //booklist.html
    }

    @PostMapping("/booklist")
    public String saveBook(@ModelAttribute Book book, Model model) {
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBook(@RequestParam(value = "id", required = false) Long id, Model model) {
        Book book = (id != null) ? bookRepository.findById(id).orElse(new Book()) : new Book();
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String saveOrUpdateBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/deletebook/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/editbook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(new Book());
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook"; // Käytetään samaa lomaketta
    }

    

}
