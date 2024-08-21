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
public class ProductRequest {
    private String product_name;
    private String body;
    private LocalDate cultivateAt;
    //private Integer memberId;
    //private Integer categoryId;
    // MultipartFile로 받음
    private List<MultipartFile> images;
    private List<ProductOptionRequest> options;

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.product_name);
        product.setBody(this.body);
        product.setCultivate_At(this.cultivateAt);
        //product.setMemberId(this.memberId);
       // product.setCategoryId(this.categoryId);
        return product;
    }

    public List<ProductOption> toProductOptions() {
        return this.options.stream()
                .map(ProductOptionRequest::toProductOption)
                .collect(Collectors.toList());
    }
}
@Getter
@Setter
class ProductOptionRequest {
    private Integer minVolume;
    private Integer unitPrice;
    private Boolean isOrganic;

    public ProductOption toProductOption() {
        ProductOption option = new ProductOption();
        option.setMin_Volume(this.minVolume);
        option.setUnit_Price(this.unitPrice);
        option.setOrganic(this.isOrganic);
        return option;
    }
}