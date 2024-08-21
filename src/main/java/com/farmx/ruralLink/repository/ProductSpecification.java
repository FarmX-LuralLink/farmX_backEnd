package com.farmx.ruralLink.repository;

import com.farmx.ruralLink.domain.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasCropType(String cropType){
        return(root, query, criteriaBuilder) ->
                cropType == null || cropType.isEmpty() ? null : criteriaBuilder.equal(root.get("name"), cropType);
    }

    public static Specification<Product> isOrganic(Boolean organic) {
        return (root, query, criteriaBuilder) ->
                organic == null ? null : criteriaBuilder.equal(root.join("productOption").get("organic"), organic);
    }

    public static Specification<Product> priceBetween(Integer minPrice, Integer maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.join("productOption").get("unitPrice"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.join("productOption").get("unitPrice"), minPrice);
            } else if (maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.join("productOption").get("unitPrice"), maxPrice);
            }
            return null;
        };
    }
}
