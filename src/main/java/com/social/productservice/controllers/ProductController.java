package com.social.productservice.controllers;


import com.social.productservice.dtos.APIExceptionDto;
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
