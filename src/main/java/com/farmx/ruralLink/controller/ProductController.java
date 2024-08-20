package com.farmx.ruralLink.controller;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductImage;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.dto.ProductRequest;
import com.farmx.ruralLink.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruralLink/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/info")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        // ProductRequest로부터 Product, ProductImage, ProductOption 데이터 분리
        Product product = productRequest.toProduct();
        List<ProductImage> images = productRequest.toProductImages();
        List<ProductOption> options = productRequest.toProductOptions();

        Product createdProduct = productService.createProduct(product, images, options);
        return ResponseEntity.ok(createdProduct);
    }
}
