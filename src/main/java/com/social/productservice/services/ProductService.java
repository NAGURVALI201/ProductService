package com.social.productservice.services;
import com.social.productservice.dtos.*;
import com.social.productservice.models.*;
import com.social.productservice.exceptions.*;
import java.util.List;

public interface ProductService {
    Product getProductById(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts() throws NoProductsFoundException;
    Product createNewProduct(ProductDto productDto) throws ProductNotCreatedException, CategoryNotFoundException;
    String deleteProductById(Long productId) throws ProductNotFoundException,RuntimeException;
    Product updateProduct(ProductDto productDto,Long productId) throws ProductNotFoundException,RuntimeException;

}
