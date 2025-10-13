package com.social.productservice.ExceptionHandlers;

import com.social.productservice.dtos.APIExceptionDto;
import com.social.productservice.exceptions.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryServiceExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<APIExceptionDto> categoryNotFoundException(CategoryNotFoundException ex){

        return new ResponseEntity<>(
                new APIExceptionDto(
                        ex.getMessage(),
                        "Pass a Category in the RequestBody."
                )
                , HttpStatus.BAD_REQUEST
        );
    }
}
