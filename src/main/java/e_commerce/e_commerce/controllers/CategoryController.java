package e_commerce.e_commerce.controllers;

import e_commerce.e_commerce.auth.entities.Category;
import e_commerce.e_commerce.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping({"/category"})

public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @PostMapping("/add")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @Valid @RequestBody Category categoryRequest) {
        return categoryRepository.findById(categoryId).map(category -> {
            category.setCategoryName(categoryRequest.getCategoryName());
            category.setParentCategoryId(categoryRequest.getParentCategoryId());

            return categoryRepository.save(category);
        }).orElseThrow(() -> new IllegalArgumentException("CategoryId " + categoryId + " not found"));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryRepository.findById(categoryId).map(category -> {
            categoryRepository.delete(category);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Category with Id " + category + " was deleted successfully");
            return ResponseEntity.ok(response);
        }).orElseThrow(() -> new IllegalArgumentException("Category not found with id " + categoryId));
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {

        Optional<Category> p = categoryRepository.findById(categoryId);

        return p.get();

    }
}

