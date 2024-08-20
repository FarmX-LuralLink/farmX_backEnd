package com.farmx.ruralLink.controller;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.dto.ProductRequest;
import com.farmx.ruralLink.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ruralLink/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/info")
    public String getProductUploadPage() {
        return "/test";
    }

    @PostMapping("/info")
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductRequest productRequest) throws IOException {

        Product product = productRequest.toProduct();
        List<ProductOption> options = productRequest.toProductOptions();

        Product createdProduct = productService.createProduct(product, productRequest.getImages(), options);
        return ResponseEntity.ok(createdProduct);
    }




}
