package com.social.productservice.controllers;


import com.social.productservice.models.*;
import com.social.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId){

        Product product = null;
        ResponseEntity<Product> responseEntity = null;
        try{
            product = productService.getProductById(productId);
            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public Product createNewProduct(@RequestBody Product product){
        return new Product();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long productId){
        return new ResponseEntity<>(null);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable Long productId){
        return new Product();
    }



}
