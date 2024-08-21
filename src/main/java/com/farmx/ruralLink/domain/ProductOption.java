package com.farmx.ruralLink.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String min_Volume;

    private Integer unit_Price;

    private Boolean organic;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // getters and setters
}
