package com.social.productservice.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.social.productservice.dtos.*;
import com.social.productservice.models.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    public String deleteProductById(Long productId) throws ProductNotFoundException
            {

        Map<String,Long> uriVariables = new HashMap<>();
        uriVariables.put("id",productId);

        ResponseEntity<String> result =  restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                uriVariables
        );

        if(result.getStatusCode().value()==400)
        {
            throw new ProductNotFoundException("Product Not found with id: "+productId);
        }
        return "Product with id: "+ productId +" is deleted successfully";
    }

    @Override
    public Product updateProduct(ProductDto productDto, Long productId) throws ProductNotFoundException,
            RuntimeException {

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class
        );
        if(fakeStoreProductDtoResponseEntity.getStatusCode().value()==400){
            throw new ProductNotFoundException("No product found with id: ",productId);
        }

        Map<String,Long> uriVariables = new HashMap<>();
        uriVariables.put("id",productId);

        FakeStoreProductRequestDto fakeStoreProductRequestDto = convertProductDtoToFakeStoreProductRequestDto(productDto);

        HttpEntity<FakeStoreProductRequestDto> requestEntity = new HttpEntity<>(fakeStoreProductRequestDto);

        fakeStoreProductDtoResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                uriVariables
        );

        if(fakeStoreProductDtoResponseEntity.getStatusCode().value()==400){
            throw new RuntimeException("unable to update the product.");
        }

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product createNewProduct(ProductDto productDto) throws ProductNotCreatedException {

        FakeStoreProductRequestDto fakeStoreProductRequestDto = convertProductDtoToFakeStoreProductRequestDto(productDto);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponse = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                fakeStoreProductRequestDto,
                    FakeStoreProductDto.class
                );

        if(fakeStoreProductDtoResponse.getStatusCode().value() == 400)
        {
            throw new ProductNotCreatedException("Unable to create product.");
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponse.getBody());
    }

    private FakeStoreProductRequestDto convertProductDtoToFakeStoreProductRequestDto(ProductDto productDto) {
        return new FakeStoreProductRequestDto(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getPrice(),
                productDto.getDescription(),
                productDto.getCategory().getTitle(),
                productDto.getImage()
        );
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
        product.setImg_url(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }


}
