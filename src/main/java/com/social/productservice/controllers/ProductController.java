package com.social.productservice.controllers;


import com.social.productservice.dtos.APIExceptionDto;
import com.social.productservice.dtos.*;
import com.social.productservice.models.*;
import com.social.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.social.productservice.exceptions.*;
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId)
    throws ProductNotFoundException
    {
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Product> getAllProducts()
    throws NoProductsFoundException
    {
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public ResponseEntity<Product> createNewProduct(@RequestBody ProductDto productDto)
    throws ProductNotCreatedException
    {
        Product response = productService.createNewProduct(productDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long productId)
    throws ProductNotFoundException,RuntimeException
    {
        System.out.println(productService.deleteProductById(productId));
        return new ResponseEntity<>(productService.deleteProductById(productId),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody ProductDto productDto,
                                 @PathVariable("id") Long productId)
            throws ProductNotFoundException,RuntimeException
    {
        return productService.updateProduct(productDto,productId);
    }

    /*
    * When there is runtime exception this class exception handler will have priority.
    */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIExceptionDto> runtimeException(RuntimeException ex) {
        String message = ex.getMessage();
        String resolution = "please try later!!!";
        APIExceptionDto apiExceptionDto = new APIExceptionDto(
                message,
                resolution
        );
        return new ResponseEntity<>(apiExceptionDto,HttpStatus.BAD_GATEWAY);
    }

}
