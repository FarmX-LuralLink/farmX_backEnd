package com.farmx.ruralLink.domain.init;



import com.farmx.ruralLink.domain.Product;
import com.farmx.ruralLink.domain.ProductImage;
import com.farmx.ruralLink.domain.ProductOption;
import com.farmx.ruralLink.repository.ProductImageRepository;
import com.farmx.ruralLink.repository.ProductOptionRepository;
import com.farmx.ruralLink.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Order(1)
@Component
public class ProductInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductInitializer(ProductRepository productRepository,
                              ProductImageRepository productImageRepository,
                              ProductOptionRepository productOptionRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.productOptionRepository = productOptionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 더미 데이터 삽입
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setBody("Description of Product 1");
        product1.setCultivate_At(LocalDate.of(1992, 12, 5));
        //product1.setMemberId(1);
        //product1.setCategoryId(1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setBody("Description of Product 2");
        product2.setCultivate_At(LocalDate.now());
        //product2.setMemberId(2);
        //product2.setCategoryId(2);

        productRepository.save(product1);
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
        option1.setMin_Volume(10);
        option1.setUnit_Price(1000);
        option1.setOrganic(Boolean.TRUE);
        option1.setProduct(product1);

        ProductOption option2 = new ProductOption();
        option2.setMin_Volume(20);
        option2.setUnit_Price(2000);
        option2.setOrganic(Boolean.TRUE);
        option2.setProduct(product2);

        productOptionRepository.save(option1);
        productOptionRepository.save(option2);


    }
}