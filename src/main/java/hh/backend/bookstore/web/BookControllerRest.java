package hh.backend.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@CrossOrigin
@RestController

public class BookControllerRest {

    private final BookRepository repository;

    public BookControllerRest(BookRepository repository) {
        this.repository= repository;
    }

    //return all books
    //muuntaa Java-kielisestä Book -luokan oliolistasta JSON-muotoisen kirjalistan, 
    //joka menee web-selaimelle.

    @GetMapping("/books")
    public List<Book> findAllBooksRest() {
        return (List<Book>) repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getOneBookRest(@PathVariable(name = "id") Long bookId) {
        return repository.findById(bookId);
    }

}
