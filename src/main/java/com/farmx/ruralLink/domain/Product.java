package com.farmx.ruralLink.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10000)
    private String body;

    private LocalDate cultivate_At;
//
//    @Column(name = "member_id", nullable = false)
//    private Integer memberId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOption> options;

    @PrePersist
    @PreUpdate
    protected void onSaveOrUpdate() {
        this.createdAt = LocalDateTime.now();
    }


}







