package com.social.productservice.services;

import com.social.productservice.dtos.ProductDto;
import com.social.productservice.exceptions.NoProductsFoundException;
import com.social.productservice.exceptions.ProductNotCreatedException;
import com.social.productservice.exceptions.ProductNotFoundException;
import com.social.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class selfProductService implements ProductService{
    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws NoProductsFoundException {
        return List.of();
    }

    @Override
    public Product createNewProduct(ProductDto productDto) throws ProductNotCreatedException {
        return null;
    }

    @Override
    public String deleteProductById(Long productId) throws ProductNotFoundException, RuntimeException {
        return "";
    }

    @Override
    public Product updateProduct(ProductDto productDto, Long productId) throws ProductNotFoundException, RuntimeException {
        return null;
    }
}
