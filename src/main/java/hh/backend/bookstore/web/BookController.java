package hh.backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hh.backend.bookstore.domain.BookRepository;

@Controller
public class BookController {

    BookRepository bookRepository;

	BookController(BookRepository bookRepository){
		this.bookRepository = bookRepository;
	}


    @GetMapping("/bookstore")
    public String WelcomeToTheSite(Model model){
        return "etusivu"; //etusivu.html
    }

    @GetMapping("/booklist")
    public String listAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist"; //booklist.html
    }
}
