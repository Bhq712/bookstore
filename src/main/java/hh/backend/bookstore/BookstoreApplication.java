package hh.backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.CategoryRepository;
import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.Category;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner BookstoreDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("save a couple of books");
			bookRepository.save(new Book("Orvot katseet", "Janice Muller", 2020, "1234567890", 13.45));
			bookRepository.save(new Book("Kaukainen tulevaisuus", "Adam Kenner", 2023, "2234567890", 19.99));
			bookRepository.save(new Book("Kultainen tie", "Talia Smith", 1999, "3234567890", 24.99));
			bookRepository.save(new Book("Tulikärpänen", "Joanna Geller", 2012, "4234567890", 21.56));

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
	
	@Bean
	public CommandLineRunner BookstoreCategory(CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("save some categories");
			categoryRepository.save(new Category("Scifi"));
			categoryRepository.save(new Category("Romance"));
			categoryRepository.save(new Category("Horror"));

			log.info("fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}
		};
	}
}
