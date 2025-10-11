package com.social.productservice.services;

import java.util.ArrayList;
import java.util.List;

import com.social.productservice.dtos.*;
import com.social.productservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.social.productservice.exceptions.*;

@Service
public class ProductServiceFakeStoreApi implements ProductService{
    // All the below Api's will be call the using the FakeStoreApi.
    // it is http client which is used to make request to other servers.

    private final RestTemplate restTemplate;

    public ProductServiceFakeStoreApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException{

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class
        );


        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDto == null)
        {
            throw new ProductNotFoundException("No product found with id: ",productId);
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public List<Product> getAllProducts() throws NoProductsFoundException {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/",FakeStoreProductDto[].class
        );

        FakeStoreProductDto[] fakeStoreProductDtoArr =  fakeStoreProductDtoResponse.getBody();

        if(fakeStoreProductDtoArr.length == 0){
            throw new NoProductsFoundException("No products Found.");
        }
        List<Product> results = new ArrayList<>();

        for(FakeStoreProductDto obj: fakeStoreProductDtoArr){
            results.add(convertFakeStoreProductDtoToProduct(obj));
        }

        return results;
    }

    @Override
    public Boolean deleteProductById(Long productId) {
        return null;
    }

    @Override
    public Product updateProduct(Product product, Long productId) {
        return null;
    }

    @Override
    public Product createNewProduct(ProductDto productDto) throws ProductNotCreatedException {

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponse = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                    productDto,
                    FakeStoreProductDto.class
                );

        if(fakeStoreProductDtoResponse.getStatusCode().value() == 400)
        {
            throw new ProductNotCreatedException("Unable to create product.");
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponse.getBody());
    }

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if(fakeStoreProductDto == null){
            return null;
        }

        Product product = new Product();

        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
