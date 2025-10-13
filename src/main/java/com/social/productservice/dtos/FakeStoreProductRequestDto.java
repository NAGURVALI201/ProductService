package com.social.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductRequestDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
