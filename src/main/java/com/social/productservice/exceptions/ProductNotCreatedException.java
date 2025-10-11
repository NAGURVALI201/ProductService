package com.social.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotCreatedException extends Exception{
    public ProductNotCreatedException(String message){
        super(message);
    }
}
