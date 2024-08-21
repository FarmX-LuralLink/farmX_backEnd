package com.farmx.ruralLink.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String minVolume;

    @Column(nullable = false)
    private int unitPrice;

    @Column(nullable = false)
    private boolean organic;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
