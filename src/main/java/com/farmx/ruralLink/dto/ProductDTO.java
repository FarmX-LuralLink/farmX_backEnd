package com.farmx.ruralLink.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String body;
    private LocalDate cultivateAt;
    private int upperCategory;
    private String lowerCategoryName;
    private String minVolume;
    private int unitPrice;
    private boolean organic;
    private String imageUrl;
}
