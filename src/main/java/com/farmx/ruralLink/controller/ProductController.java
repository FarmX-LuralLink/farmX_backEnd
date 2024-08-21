package com.farmx.ruralLink.controller;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.dto.ProductDTO;
import com.farmx.ruralLink.dto.ProductRequestDTO;
import com.farmx.ruralLink.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

//    @GetMapping("/info")
//    public String getProductUploadPage(Model model) {
//        // 채소와 과일 목록 생성
//        List<String> vegetables = Arrays.asList("당근", "무", "양파", "배추", "파");
//        List<String> fruits = Arrays.asList("딸기", "배", "레몬", "귤", "사과");
//
//
//        Map<String, List<String>> productList = new HashMap<>();
//        productList.put("채소", vegetables);
//        productList.put("과일", fruits);
//
//        model.addAttribute("product_list", productList);
//
//
//        return "/test";
//    }
    @PostMapping("/info")
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductRequestDTO productRequest) throws IOException {

        Product product = productRequest.toProduct();
        List<ProductOption> options = productRequest.toProductOptions();

        Product createdProduct = productService.createProduct(product, productRequest.getImages(), options);
        return ResponseEntity.ok(createdProduct);
    }
}
