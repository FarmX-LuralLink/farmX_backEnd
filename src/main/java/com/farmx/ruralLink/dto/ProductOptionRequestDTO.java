package com.farmx.ruralLink.dto;

import com.farmx.ruralLink.domain.ProductOption;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ProductOptionRequestDTO {
    private String minVolume;
    private Integer unitPrice;
    private Boolean organic;

    public ProductOption toProductOption() {
        ProductOption option = new ProductOption();
        option.setMinVolume(this.minVolume);
        option.setUnitPrice(this.unitPrice);
        option.setOrganic(this.organic);
        return option;
    }
}
