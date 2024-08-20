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

        // S3에 이미지 업로드 및 ProductImage 저장
        for (MultipartFile imageFile : images) {
            String imageUrl = s3Service.uploadFile(imageFile);
            ProductImage image = new ProductImage();
            image.setUrl1(imageUrl);
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
