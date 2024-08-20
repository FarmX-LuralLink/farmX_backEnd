package com.farmx.ruralLink.dto;

import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductImage;
import com.farmx.ruralLink.domain.ProductOption;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String body;
    private LocalDate cultivateAt;
    private Integer memberId;
    private Integer categoryId;
    private List<ProductImageRequest> images;
    private List<ProductOptionRequest> options;

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name);
        product.setBody(this.body);
        product.setCultivate_At(this.cultivateAt);
        product.setMemberId(this.memberId);
        product.setCategoryId(this.categoryId);
        return product;
    }

    public List<ProductImage> toProductImages() {
        return this.images.stream()
                .map(ProductImageRequest::toProductImage)
                .collect(Collectors.toList());
    }

    public List<ProductOption> toProductOptions() {
        return this.options.stream()
                .map(ProductOptionRequest::toProductOption)
                .collect(Collectors.toList());
    }
}

@Getter
@Setter
class ProductImageRequest {
    private String url1;
    private String url2;
    private String url3;

    public ProductImage toProductImage() {
        ProductImage image = new ProductImage();
        image.setUrl1(this.url1);
        image.setUrl2(this.url2);
        image.setUrl3(this.url3);
        return image;
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
