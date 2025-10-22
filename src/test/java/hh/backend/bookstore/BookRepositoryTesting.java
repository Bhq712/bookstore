package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTesting {

   private final CategoryRepository categoryRepository;
   private final BookRepository bookRepository;

public BookRepositoryTesting(BookRepository bookRepository, CategoryRepository categoryRepository) {
    this.bookRepository = bookRepository;
    this.categoryRepository = categoryRepository;
}

@Test
public void createNewBookWithCategory() {
    Category romance = new Category("Romance");
    categoryRepository.save(romance);

    Book book = new Book(
        "Auringonlaskun kylä",
        "Jesse Konkarinen",
        1993,
        "9847361383",
        45.65,
        romance
    );
    bookRepository.save(book);
    assertThat(book.getId()).isNotNull();
}

  @Test
    public void findByTitleShouldReturnAuthor() {
        List<Book> books = bookRepository.findByTitle("Auringonlaskun kylä");
        assertThat(books.get(0).getAuthor()).isEqualTo("Jesse Konkarinen");
    }

    @Test
    public void deleteBook() {
        Category romance = new Category("Romance");
        categoryRepository.save(romance);

        Book book = new Book(
            "Auringonlaskun kylä",
            "Jesse Konkarinen",
            1993,
            "9847361383",
            45.65,
            romance
        );
        bookRepository.save(book);

        bookRepository.delete(book);
        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }

}
