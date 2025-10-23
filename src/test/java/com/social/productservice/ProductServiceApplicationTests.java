package com.social.productservice;

import com.social.productservice.Repositories.CategoryRepository;
import com.social.productservice.Repositories.ProductRepository;
import com.social.productservice.models.Category;
import com.social.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    ProductServiceApplicationTests(ProductRepository productRepository,
                                   CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Test
    void contextLoads() {

    }
//    @Test
//    void test_findProduct(){
//        Long productId = 1L;
//
//        Optional<Product> product = productRepository.findProductById(productId);
//
//        if(product.isPresent()){
//            System.out.println("hql" + product.get().getTitle());
//        }
//
//
//        product = productRepository.findProductUsingId(productId);
//
//        if(product.isPresent()){
//            System.out.println("sql" + product.get().getTitle());
//        }
//    }

    @Test
    @Transactional
    void testFetchTypes(){

        // if we don't write @Transactional every single db call is treated as a individual transaction.
        // EAGER
        Optional<Product> productOptional = productRepository.findById(3L);
        // LAZY
        // Since category has collection of type products it will not fetch it by default
        // so should we make EAGER then no it is not good to get all the products when getting the category

        Optional<Category> categoryOptional = categoryRepository.findById(2L);

        // we can simply get the products by using the above category and call get on it.
        // we should use @Transactional on the method because we are using the value of another transaction
        // which is completed.
        List<Product> products = categoryOptional.get().getProducts();
        // this will call the sql query in db
        // select * from products where category_id = 2L;
        System.out.println(products.get(0).getTitle());
    }

}
