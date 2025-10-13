package com.social.productservice.services;

import com.social.productservice.Repositories.CategoryRepository;
import com.social.productservice.Repositories.ProductRepository;
import com.social.productservice.dtos.ProductDto;
import com.social.productservice.exceptions.CategoryNotFoundException;
import com.social.productservice.exceptions.NoProductsFoundException;
import com.social.productservice.exceptions.ProductNotCreatedException;
import com.social.productservice.exceptions.ProductNotFoundException;
import com.social.productservice.models.Category;
import com.social.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    )
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {

        return productRepository.findById(productId)
                .orElseThrow(
                        ()-> new ProductNotFoundException("product is not present with id: ",productId)
                );
    }

    @Override
    public List<Product> getAllProducts() throws NoProductsFoundException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new NoProductsFoundException("No products found.");
        }
        return products;
    }

    @Override
    public Product createNewProduct(ProductDto productDto) throws ProductNotCreatedException, CategoryNotFoundException {
        Product product = productDto.toProduct();
        Category category = product.getCategory();

        if(category == null )
        {
            throw new CategoryNotFoundException("Product can't be created without a Category.");
        }

        Optional<Category> categoryDB = categoryRepository.findByTitle(
                category.getTitle()
        );

        if(categoryDB.isEmpty()){
            category = categoryRepository.save(category);
        }else{
            category = categoryDB.get();
        }
        product.setCategory(category);
        product = productRepository.save(product);

        return product;
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
