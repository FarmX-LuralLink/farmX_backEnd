package com.farmx.ruralLink.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10000)
    private String body;
    private LocalDate cultivateAt;
    private int upperCategory;  // 0: 채소, 1: 과일

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category lowerCategory;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductOption productOption;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductImage productImage;

    @PrePersist
    @PreUpdate
    protected void onSaveOrUpdate() {
        this.createdAt = LocalDateTime.now();
    }

}