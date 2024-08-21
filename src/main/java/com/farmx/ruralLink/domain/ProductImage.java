package com.farmx.ruralLink.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2083, nullable = false)
    private String url1;

    @Column(length = 2083)
    private String url2;

    @Column(length = 2083)
    private String url3;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
