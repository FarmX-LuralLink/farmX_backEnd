package com.farmx.ruralLink.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

}
