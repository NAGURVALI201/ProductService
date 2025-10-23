package com.social.productservice.services;

import com.social.productservice.exceptions.CategoryNotFoundException;

public interface CategoryService {
    public String deleteById(Long id) throws CategoryNotFoundException;
}
