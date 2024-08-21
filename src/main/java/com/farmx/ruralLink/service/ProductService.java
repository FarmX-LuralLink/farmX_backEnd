package com.farmx.ruralLink.service;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductImage;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.repository.ProductImageRepository;
import com.farmx.ruralLink.repository.ProductOptionRepository;
import com.farmx.ruralLink.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    private final S3Service s3Service;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductImageRepository productImageRepository,
                          ProductOptionRepository productOptionRepository,
                          S3Service s3Service) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.productOptionRepository = productOptionRepository;
        this.s3Service = s3Service;
    }

    public Product createProduct(Product product, List<MultipartFile> images, List<ProductOption> options) throws IOException {
        // Product 저장
        Product savedProduct = productRepository.save(product);

        // ProductImage 생성
        ProductImage productImage = new ProductImage();
        productImage.setProduct(savedProduct);

        // 최대 3개의 이미지만 처리
        for (int i = 0; i < images.size() && i < 3; i++) {
            String imageUrl = s3Service.uploadFile(images.get(i));

            if (i == 0) {
                productImage.setUrl1(imageUrl);
            } else if (i == 1) {
                productImage.setUrl2(imageUrl);
            } else if (i == 2) {
                productImage.setUrl3(imageUrl);
            }
        }

        // ProductImage 저장
        productImageRepository.save(productImage);

        // ProductOption 저장
        for (ProductOption option : options) {
            option.setProduct(savedProduct);
            productOptionRepository.save(option);
        }

        return savedProduct;
    }
}

