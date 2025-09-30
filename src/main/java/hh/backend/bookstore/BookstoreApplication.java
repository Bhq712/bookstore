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
import hh.backend.bookstore.domain.User;
import hh.backend.bookstore.domain.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner BookstoreDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("save a couple of books with categories");
			Category scifi = categoryRepository.findByName("Scifi").iterator().hasNext() ? categoryRepository.findByName("Scifi").iterator().next() : categoryRepository.save(new Category("Scifi"));
			Category romance = categoryRepository.findByName("Romance").iterator().hasNext() ? categoryRepository.findByName("Romance").iterator().next() : categoryRepository.save(new Category("Romance"));
			Category horror = categoryRepository.findByName("Horror").iterator().hasNext() ? categoryRepository.findByName("Horror").iterator().next() : categoryRepository.save(new Category("Horror"));

			bookRepository.save(new Book("Orvot katseet", "Janice Muller", 2020, "1234567890", 13.45, romance));
			bookRepository.save(new Book("Kaukainen tulevaisuus", "Adam Kenner", 2023, "2234567890", 19.99, scifi));
			bookRepository.save(new Book("Kultainen tie", "Talia Smith", 1999, "3234567890", 24.99, horror));
			bookRepository.save(new Book("Tulikärpänen", "Joanna Geller", 2012, "4234567890", 21.56, romance));

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
	
	@Bean
	public CommandLineRunner initUsers(UserRepository userRepository) {
		return (args) -> {
			// Tarkista onko käyttäjiä jo olemassa
			if (userRepository.findByUsername("user") == null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				
				log.info("Creating users with BCrypt hashed passwords");
				
				// Luo USER-roolin käyttäjä
				User user1 = new User();
				user1.setUsername("user");
				user1.setPassword(passwordEncoder.encode("user"));
				user1.setEmail("user@example.com");
				user1.setRole("USER");
				userRepository.save(user1);
				
				// Luo ADMIN-roolin käyttäjä
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setEmail("admin@example.com");
				admin.setRole("ADMIN");
				userRepository.save(admin);

				// Luo Tiia -roolin käyttäjä
				User tiia = new User();
				tiia.setUsername("tiia");
				tiia.setPassword(passwordEncoder.encode("tiia123"));
				tiia.setEmail("tiia@example.com");
				tiia.setRole("USER");
				userRepository.save(tiia);
				
				log.info("Users created successfully");
			} else {
				log.info("Users already exist in database");
			}
		};
	}
}
