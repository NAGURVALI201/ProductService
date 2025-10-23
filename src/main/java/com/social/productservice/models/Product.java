package com.social.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
* By Default all the non collection properties fetch type is EAGER.
* By Default all the collection properties fetch type is LAZY.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product extends BaseModel{
    private String title;
    private Double price;
    private String description;
    private String img_url;
    @JsonIgnoreProperties("products")
    @ManyToOne
    private Category category;

}
