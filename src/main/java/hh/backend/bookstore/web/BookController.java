package hh.backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@Controller
public class BookController {

    BookRepository bookRepository;

	BookController(BookRepository bookRepository){
		this.bookRepository = bookRepository;
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

    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook"; // addbook.html
    }

    @PostMapping("/booklist")
    public String saveBook(@ModelAttribute Book book, Model model) {
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @PostMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookRepository.deleteById(id);
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

}
