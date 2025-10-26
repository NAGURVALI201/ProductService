package com.social.productservice.controllers;

import com.social.productservice.exceptions.ProductNotFoundException;
import com.social.productservice.models.Product;
import com.social.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    // injecting the fake object
    @MockitoBean
    private ProductService productService;

    @Autowired
    private ProductController productController;

    // AAA
//    @Test
//    public void getProductByIdTestPositiveCase() throws ProductNotFoundException {
//        // Arrange
//        Long productId = 10L;
//        Product expectedProduct = new Product();
//        expectedProduct.setId(productId);
//        expectedProduct.setTitle("iphone 16");
//        expectedProduct.setPrice(70000.0);
//
//        when(productService.getProductById(productId)).thenReturn(expectedProduct);
//        // Act
//        Product actualProduct = productController.getProductById(productId);
//
//        // Assert
//        assertEquals(expectedProduct.getId(),actualProduct.getId());
//        assertEquals("iphone 16",actualProduct.getTitle());
//        assertEquals(70000.0,actualProduct.getPrice());
//    }

//    @Test
//    public void getProductByIdTestProductNotFoundException() throws ProductNotFoundException {
//
//        // Arrange
//        ProductNotFoundException productNotFoundException=
//                new ProductNotFoundException("No product with id: -1");
//
//        when(productService.getProductById(-1L)).thenThrow(productNotFoundException);
//
//        // Assert & Act
//        Exception exception = assertThrows(
//                ProductNotFoundException.class,
//                ()-> productService.getProductById(-1L)
//        );
//
//        assertEquals(
//                productNotFoundException.getMessage(),
//                exception.getMessage()
//        );
//
//    }
    @Test
    public void getProductByIdTestNegativeCase(){

    }
}