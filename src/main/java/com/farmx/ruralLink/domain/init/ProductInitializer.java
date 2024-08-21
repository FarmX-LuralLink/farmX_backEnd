package com.farmx.ruralLink.domain.init;


import com.farmx.ruralLink.domain.Category;
import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductImage;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.repository.CategoryRepository;
import com.farmx.ruralLink.repository.ProductImageRepository;
import com.farmx.ruralLink.repository.ProductOptionRepository;
import com.farmx.ruralLink.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Order(1) @Component
@AllArgsConstructor
public class ProductInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        // 상위 카테고리 생성
        Category vegetableCategory = new Category();
        vegetableCategory.setName("채소");
        categoryRepository.save(vegetableCategory);

        Category fruitCategory = new Category();
        fruitCategory.setName("과일");
        categoryRepository.save(fruitCategory);

        // 하위 카테고리 생성
        Category carrotCategory = new Category();
        carrotCategory.setName("당근");
        carrotCategory.setParentCategory(vegetableCategory);
        categoryRepository.save(carrotCategory);

        Category appleCategory = new Category();
        appleCategory.setName("사과");
        appleCategory.setParentCategory(fruitCategory);
        categoryRepository.save(appleCategory);

        // 더미 데이터 삽입
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setBody("Description of Product 1");
        product1.setCultivateAt(LocalDate.of(1992, 12, 5));
        product1.setLowerCategory(carrotCategory); // 설정된 하위 카테고리 사용
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setBody("Description of Product 2");
        product2.setCultivateAt(LocalDate.now());
        product2.setLowerCategory(appleCategory); // 설정된 하위 카테고리 사용
        productRepository.save(product2);

        // Product 1의 이미지와 옵션 데이터 삽입
        ProductImage image1 = new ProductImage();
        image1.setUrl1("http://example.com/image1_1.jpg");
        image1.setUrl2("http://example.com/image1_2.jpg");
        image1.setUrl3("http://example.com/image1_3.jpg");
        image1.setProduct(product1);

        ProductImage image2 = new ProductImage();
        image2.setUrl1("http://example.com/image2_1.jpg");
        image2.setUrl2("http://example.com/image2_2.jpg");
        image2.setUrl3("http://example.com/image2_3.jpg");
        image2.setProduct(product2);

        productImageRepository.save(image1);
        productImageRepository.save(image2);

        ProductOption option1 = new ProductOption();
        option1.setMinVolume("10");
        option1.setUnitPrice(1000);
        option1.setOrganic(Boolean.TRUE);
        option1.setProduct(product1);

        ProductOption option2 = new ProductOption();
        option2.setMinVolume("20");
        option2.setUnitPrice(2000);
        option2.setOrganic(Boolean.TRUE);
        option2.setProduct(product2);

        productOptionRepository.save(option1);
        productOptionRepository.save(option2);
    }
}