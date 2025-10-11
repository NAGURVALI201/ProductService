package com.social.productservice.ExceptionHandlers;

import com.social.productservice.dtos.*;
import com.social.productservice.exceptions.NoProductsFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.social.productservice.exceptions.*;
@ControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIExceptionDto> productNotFoundException(ProductNotFoundException ex){
        String responseMessage = ex.getMessage() +" "+ex.getId();
        String resolutionCode = "Their are only 20 products with ids 1..20";
        APIExceptionDto apiExceptionDto = new APIExceptionDto(
                responseMessage,
                resolutionCode
                );
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoProductsFoundException.class)
    public ResponseEntity<APIExceptionDto> NoproductsFoundException(NoProductsFoundException ex){
        String responseMessage = ex.getMessage() ;
        String resolutionCode = "The Product will be added in sometime";
        APIExceptionDto apiExceptionDto = new APIExceptionDto(
                responseMessage,
                resolutionCode
        );
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotCreatedException.class)
    public ResponseEntity<APIExceptionDto> ProductNotCreatedException(ProductNotCreatedException ex){
        String responseMessage = ex.getMessage() ;
        String resolutionCode = "pass the correct json body.";
        APIExceptionDto apiExceptionDto = new APIExceptionDto(
                responseMessage,
                resolutionCode
        );
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.BAD_REQUEST);
    }

    /*
    * we can have exception in the controller as well because if we want to handle different types
    * behaviour for handling specific condition
    * for product service runtime one type of behaviour and for other service runtime exception
    * we need other behaviour then we can add handler the product controller class. then it will get priority.
    */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIExceptionDto> runtimeException(RuntimeException ex) {
        String message = ex.getLocalizedMessage();
        String resolution = "please try later";
        APIExceptionDto apiExceptionDto = new APIExceptionDto(
                message,
                resolution
        );
        return new ResponseEntity<>(apiExceptionDto,HttpStatus.BAD_GATEWAY);
    }
}
