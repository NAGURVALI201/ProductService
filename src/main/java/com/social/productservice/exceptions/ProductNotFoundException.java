package com.social.productservice.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception {
    private Long id;
    public  ProductNotFoundException(String message,Long id){
        super(message);
        this.id = id;
    }
    public ProductNotFoundException(String message){
        super(message);
    }
}
