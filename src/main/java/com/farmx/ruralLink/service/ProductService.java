package com.farmx.ruralLink.service;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductImage;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.dto.ProductDTO;
import com.farmx.ruralLink.repository.ProductImageRepository;
import com.farmx.ruralLink.repository.ProductOptionRepository;
import com.farmx.ruralLink.repository.ProductRepository;
import com.farmx.ruralLink.repository.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    private final S3Service s3Service;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getBody(),
                product.getCultivateAt(),
                product.getUpperCategory(),
                product.getLowerCategory().getName(),
                product.getProductOption().getMinVolume(),
                product.getProductOption().getUnitPrice(),
                product.getProductOption().isOrganic(),
                product.getProductImage().getUrl1() // url1만 가져옴
        );
    }

    public List<Product> filterProducts(Optional<String> cropType, Optional<Boolean> organic, Optional<Integer> minPrice, Optional<Integer> maxPrice) {
        Specification<Product> specification = Specification.where(null);

        if (cropType.isPresent()) {
            specification = specification.and(ProductSpecification.hasCropType(cropType.get()));
        }
        if (organic.isPresent()) {
            specification = specification.and(ProductSpecification.isOrganic(organic.get()));
        }
        if (minPrice.isPresent() || maxPrice.isPresent()) {
            specification = specification.and(ProductSpecification.priceBetween(minPrice.orElse(null), maxPrice.orElse(null)));
        }

        return productRepository.findAll(specification);
    }

}