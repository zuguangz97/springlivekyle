package net.nvsoftware.springlive.controller;

import net.nvsoftware.springlive.model.Product;
import net.nvsoftware.springlive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/v1/product")

public class ProductController {
    @Qualifier("productServiceImpl")
    @Autowired
    private ProductService productService;
    @PostMapping("")
    public Product save(@RequestBody Product product) {
        productService.save(product);
        return product;
    }
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        return productService.deleteByID(id);
    }
}
