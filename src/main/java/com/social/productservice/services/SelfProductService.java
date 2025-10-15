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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Product createNewProduct(ProductDto productDto) throws CategoryNotFoundException {

        if(
                productDto.getCategory() == null || productDto.getCategory().getTitle() == null
                || productDto.getCategory().getTitle().isBlank()
        )
        {
            throw new CategoryNotFoundException("Product can't be created without a Category.");
        }

        Product product = productDto.toProduct();
        Category category = product.getCategory();

        Category categoryFromDB = categoryRepository.findByTitle(
                category.getTitle()
        ).orElseGet(
                () -> categoryRepository.save(category)
        );

        product.setCategory(categoryFromDB);
        return productRepository.save(product);

    }

    @Override
    @Transactional
    public Product updateProduct(ProductDto productDto, Long productId) throws ProductNotFoundException,
            CategoryNotFoundException {

        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(
                        ()-> new ProductNotFoundException("Product not found with id: ",productId)
                );

        if(
                productDto.getCategory() == null || productDto.getCategory().getTitle() == null
                || productDto.getCategory().getTitle().isBlank()

        ){
            throw new CategoryNotFoundException("Product can't be updated without a valid category.");
        }

        Category category = categoryRepository.findByTitle(productDto.getCategory().getTitle())
                .orElseThrow(
                        ()->new CategoryNotFoundException("Invalid Category please provide a correct Catgroy name.")
                );

        productFromDB.setCategory(category);

        if(productDto.getTitle()!=null)
            productFromDB.setTitle(productDto.getTitle());

        if(productDto.getDescription()!=null)
            productFromDB.setDescription(productDto.getDescription());

        if(productDto.getPrice()!=null)
            productFromDB.setPrice(productDto.getPrice());

        if(productDto.getImage()!=null)
            productFromDB.setImageUrl(productDto.getImage());

        return productRepository.save(productFromDB);
    }


    @Override
    public String deleteProductById(Long productId) throws ProductNotFoundException {
        if(!productRepository.existsById(productId)){
            throw new ProductNotFoundException("Product not found with id: ",productId);
        }
        productRepository.deleteById(productId);
        return "Product Deleted successfully.";
    }


}
