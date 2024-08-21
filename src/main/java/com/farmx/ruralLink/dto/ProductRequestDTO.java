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
    private String name;
    private String body;
    private LocalDate cultivateAt;
    private List<MultipartFile> images;
    private List<ProductOptionRequestDTO> options;

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name);
        product.setBody(this.body);
        product.setCultivateAt(this.cultivateAt);

        return product;
    }

    public List<ProductOption> toProductOptions(Product product) { // Product를 매개변수로 받도록 수정
        return this.options.stream()
                .map(optionDTO -> {
                    ProductOption option = optionDTO.toProductOption();
                    option.setProduct(product); // Product와 연관 설정
                    return option;
                })
                .collect(Collectors.toList());
    }
}
