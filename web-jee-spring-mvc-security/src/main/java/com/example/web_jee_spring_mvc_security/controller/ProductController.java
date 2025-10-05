package com.example.web_jee_spring_mvc_security.controller;

import com.example.web_jee_spring_mvc_security.entities.Product;
import com.example.web_jee_spring_mvc_security.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", Product.builder().build());
        return "product/new";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "product/new";
        productRepository.save(product);
        return "redirect:/products/list";
    }
}
