package com.farmx.ruralLink.service;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductImage;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.repository.ProductImageRepository;
import com.farmx.ruralLink.repository.ProductOptionRepository;
import com.farmx.ruralLink.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductImageRepository productImageRepository,
                          ProductOptionRepository productOptionRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.productOptionRepository = productOptionRepository;
    }

    public Product createProduct(Product product, List<ProductImage> images, List<ProductOption> options) {
        // Product 저장
        Product savedProduct = productRepository.save(product);

        // ProductImage 저장
        for (ProductImage image : images) {
            image.setProduct(savedProduct);
            productImageRepository.save(image);
        }

        // ProductOption 저장
        for (ProductOption option : options) {
            option.setProduct(savedProduct);
            productOptionRepository.save(option);
        }

        return savedProduct;
    }
}
