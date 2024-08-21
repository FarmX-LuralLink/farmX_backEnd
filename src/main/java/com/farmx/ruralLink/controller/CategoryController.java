package com.farmx.ruralLink.controller;

import com.farmx.ruralLink.domain.Category;
import com.farmx.ruralLink.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ruralLink/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/subcategories")
    public List<Category> getSubcategories(@RequestParam Long parentCategoryId) {
        return categoryRepository.findByParentCategoryId(parentCategoryId);
    }
}