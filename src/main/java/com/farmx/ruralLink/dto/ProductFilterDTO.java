package com.farmx.ruralLink.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductFilterDTO {
    private String cropType;    // 작물 이름
    private Boolean organic;    // 유기농 여부
    private Integer minPrice;   // 최소 금액
    private Integer maxPrice;   // 최대 금액
}
