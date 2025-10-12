package com.social.productservice.Repositories;

import com.social.productservice.models.Category;
import com.social.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // select * from Product where id = ?
    @Override
    Optional<Product> findById(Long productId);

    // select * from Product where lower(title) like %iphone%
    List<Product> findByTitleContainingIgnoreCase(String title);

    List<Product> findByPriceBetween(Double priceMin, Double priceMax);

    // select * from Products where category.id = ?1
    List<Product> findByCategory(Category category);

    // find products by category title
    // we have to use model_attrib to find by particular column.
    // since we are searching by category title which is not present in product class
    // model_attrib notation we have to follow.
    List<Product> findAllByCategory_Title(String title);

    List<Product> findAllByCategory_Id(Long id);
}
