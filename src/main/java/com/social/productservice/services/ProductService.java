package com.social.productservice.services;
import com.social.productservice.models.*;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product createNewProduct(Product product);
    Boolean deleteProductById(Long productId);
    Product updateProduct(Product product,Long productId);
    Product updateEntireProduct(Product product,Long productId);
}
