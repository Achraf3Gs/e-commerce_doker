package e_commerce.e_commerce.controllers;


import e_commerce.e_commerce.auth.entities.Product;
import e_commerce.e_commerce.repositories.CategoryRepository;
import e_commerce.e_commerce.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

@RequestMapping({"/products"})
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository =categoryRepository;
    }


    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping("/add/{categoryId}")
    public Product createProduct(@PathVariable(value = "categoryId") Long categoryId,
                                 @Valid @RequestBody Product product) {
        return categoryRepository.findById(categoryId).map(category -> {
            product.setCategory(category);
            product.setCreatedDate(LocalDateTime.now()); // Set the current date and time
            return productRepository.save(product);
        }).orElseThrow(() -> new IllegalArgumentException("CategoryId " + categoryId + " not found"));
    }


    @PutMapping("/update/{categoryId}/{productId}")
    public Product updateProduct(@PathVariable (value = "categoryId") Long categoryId,
                                 @PathVariable (value = "productId") Long productId,
                                 @Valid @RequestBody Product productRequest) {
        if(!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("CategoryId " + categoryId + " not found");
        }

        return productRepository.findById(productId).map(product -> {
            product.setProductSKU(productRequest.getProductSKU());
            product.setProductName(productRequest.getProductName());
            product.setProductShortName(productRequest.getProductShortName());
            product.setProductPrice(productRequest.getProductPrice());
            product.setLastModificaction(LocalDateTime.now());
            product.setDeliveryTimeSpan(productRequest.getDeliveryTimeSpan());
            product.setProductImageUrl(productRequest.getProductImageUrl());
            product.setProductDescription(productRequest.getProductDescription());
            return productRepository.save(product);
        }).orElseThrow(() -> new IllegalArgumentException("ProductId " + productId + "not found"));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable(value = "productId") Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product with Id " + productId + " was deleted successfully");
            return ResponseEntity.ok(response);
        }).orElseThrow(() -> new IllegalArgumentException("Product not found with id " + productId));
    }



    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {

        Optional<Product> p = productRepository.findById(productId);

        return p.get();

    }
}

