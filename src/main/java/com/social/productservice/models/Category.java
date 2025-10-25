package com.social.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "categories")
public class Category extends BaseModel{
    private String title;

    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Product> products;
}
