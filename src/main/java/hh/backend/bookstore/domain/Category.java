package hh.backend.bookstore.domain;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //attribuutit

    private Long categoryId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonIgnore
	private List<Book> books;

     //konstruktorit

     public Category() {
        this.name = null;
    }

    public Category(String name) {
        this.name = name;
    }

    //getit ja setit

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

    //toString

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", name=" + name + "]";
    }
   

}
