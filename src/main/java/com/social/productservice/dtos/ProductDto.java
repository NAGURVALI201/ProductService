package com.social.productservice.dtos;


import com.social.productservice.models.Category;
import com.social.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private CategoryDto category;
    private String image;

    public Product toProduct(){
        Category category1 = new Category();

        category1.setId(this.category.getId());
        category1.setTitle(this.category.getTitle());

        Product product = new Product();

        product.setId(this.id);
        product.setImg_url(this.image);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setTitle(this.title);
        product.setCategory(category1);

        return product;
    }
}
