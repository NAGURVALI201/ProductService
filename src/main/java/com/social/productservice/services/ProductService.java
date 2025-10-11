package com.social.productservice.services;
import com.social.productservice.models.*;
import com.social.productservice.exceptions.*;
import java.util.List;

public interface ProductService {
    Product getProductById(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts() throws NoProductsFoundException;
    Product createNewProduct(Product product);
    Boolean deleteProductById(Long productId);
    Product updateProduct(Product product,Long productId);

}
