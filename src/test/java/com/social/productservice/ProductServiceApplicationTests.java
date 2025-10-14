package com.social.productservice;

import com.social.productservice.Repositories.ProductRepository;
import com.social.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    private final ProductRepository productRepository;

    @Autowired
    ProductServiceApplicationTests(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    void contextLoads() {

    }
    @Test
    void test_findProduct(){
        Long productId = 1L;

        Optional<Product> product = productRepository.findProductById(productId);

        if(product.isPresent()){
            System.out.println("hql" + product.get().getTitle());
        }


        product = productRepository.findProductUsingId(productId);

        if(product.isPresent()){
            System.out.println("sql" + product.get().getTitle());
        }
    }

}
