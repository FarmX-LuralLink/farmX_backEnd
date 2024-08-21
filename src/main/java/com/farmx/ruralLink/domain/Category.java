package com.farmx.ruralLink.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;    // 작물 이름

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;  // 상위 카테고리

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;  // 하위 카테고리 목록

}
