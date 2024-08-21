package com.farmx.ruralLink.service;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.dto.ProductDTO;
import com.farmx.ruralLink.repository.ProductRepository;
import com.farmx.ruralLink.repository.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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