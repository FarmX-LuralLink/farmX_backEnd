package com.farmx.ruralLink.controller;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.dto.ProductRequest;
import com.farmx.ruralLink.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ruralLink/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/info")
    public String getProductUploadPage(Model model) {
        // 채소와 과일 목록 생성
        List<String> vegetables = Arrays.asList("당근", "무", "양파", "배추", "파");
        List<String> fruits = Arrays.asList("딸기", "배", "레몬", "귤", "사과");


        Map<String, List<String>> productList = new HashMap<>();
        productList.put("채소", vegetables);
        productList.put("과일", fruits);

        model.addAttribute("product_list", productList);


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
