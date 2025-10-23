package com.social.productservice.services;

import com.social.productservice.Repositories.CategoryRepository;
import com.social.productservice.Repositories.ProductRepository;
import com.social.productservice.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class selfCategoryService implements CategoryService{
    private final CategoryRepository categoryRepository;

    public selfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String deleteById(Long id) throws CategoryNotFoundException {
        if(!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException("Category not found with id: "+id);
        }
        categoryRepository.deleteById(id);
        return "Category deleted successfully.";
    }
}
