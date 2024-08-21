package com.farmx.ruralLink.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2083)
    private String url1;

    @Column(length = 2083)
    private String url2;

    @Column(length = 2083)
    private String url3;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // getters and setters
}
