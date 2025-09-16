package hh.backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@Controller

public class CategoryController {

    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categorylist")
    public String listAllCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist"; //categorylist.html
    }

    @GetMapping("/addcategory")
    public String addCategory(@RequestParam(value = "id", required = false) Long id, Model model){
        Category category = (id != null) ? categoryRepository.findById(id).orElse(new Category()) : new Category();
        model.addAttribute("category", category);
        return "addcategory"; //addcategory.html
    }

    @PostMapping("/addcategory")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist"; // menee takaisin categorylist.html selaimessa
    }


}
