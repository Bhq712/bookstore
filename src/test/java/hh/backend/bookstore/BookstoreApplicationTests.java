package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.bookstore.web.BookController;
import hh.backend.bookstore.web.CategoryController;


@SpringBootTest
public class BookstoreApplicationTests {

    private final BookController bookController;
    private final CategoryController categoryController;

    public BookstoreApplicationTests(BookController bookController, CategoryController categoryController) {
        this.bookController = bookController;
        this.categoryController = categoryController;
    }

    @Test
    public void contextLoads() {
        assertThat(bookController).isNotNull();
        assertThat(categoryController).isNotNull();
    }

}
