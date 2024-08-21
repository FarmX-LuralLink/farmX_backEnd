package com.farmx.ruralLink.dto;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductOption;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;  // 필드명 수정
    private String body;
    private LocalDate cultivateAt;
    private List<MultipartFile> images;
    private List<ProductOptionRequestDTO> options;  // 수정: 올바른 타입 사용

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name);
        product.setBody(this.body);
        product.setCultivateAt(this.cultivateAt);

        return product;
    }

    public List<ProductOption> toProductOptions() {
        return this.options.stream()
                .map(ProductOptionRequestDTO::toProductOption)
                .collect(Collectors.toList());
    }
}
