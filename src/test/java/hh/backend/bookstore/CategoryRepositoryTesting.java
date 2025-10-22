package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import hh.backend.bookstore.domain.CategoryRepository;
import hh.backend.bookstore.domain.Category;

@DataJpaTest
public class CategoryRepositoryTesting {

    private final CategoryRepository categoryRepository;

    public CategoryRepositoryTesting(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
    }

    @Test
    public void createNewCategory() {
        Category supernatural = new Category("Supernatural");
        Category saved = categoryRepository.save(supernatural);
        assertThat(saved.getCategoryId()).isNotNull();
    }

    @Test
    public void listAllCategories() {
    categoryRepository.save(new Category("Supernatural"));
    categoryRepository.save(new Category("Mystery"));

    
    Iterable<Category> it = categoryRepository.findAll();
    List<Category> categories = new ArrayList<>();
    it.forEach(categories::add);

    
    assertThat(categories).isNotEmpty();

}

 @Test
 public void deleteCategory() {
    Category romance = new Category("Romance");
    categoryRepository.save(romance);
    categoryRepository.delete(romance);
        assertThat(categoryRepository.findById(romance.getCategoryId())).isEmpty();
 }

}
