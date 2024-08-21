package com.farmx.ruralLink.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String body;
    private LocalDate cultivateAt;

    private int upperCategory;  // 0: 채소, 1: 과일

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category lowerCategory;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductOption productOption;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductImage productImage;
}
