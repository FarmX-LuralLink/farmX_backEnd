package com.farmx.ruralLink.controller;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.dto.ProductDTO;
import com.farmx.ruralLink.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ruralLink/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam Optional<String> cropType,
            @RequestParam Optional<Boolean> organic,
            @RequestParam Optional<Integer> minPrice,
            @RequestParam Optional<Integer> maxPrice) {
        return productService.filterProducts(cropType, organic, minPrice, maxPrice);
    }

}
