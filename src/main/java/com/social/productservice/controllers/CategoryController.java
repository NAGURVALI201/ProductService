package com.social.productservice.controllers;

import com.social.productservice.exceptions.CategoryNotFoundException;
import com.social.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByCategoryId(@PathVariable("id") Long id) throws CategoryNotFoundException {
        String response = categoryService.deleteById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
