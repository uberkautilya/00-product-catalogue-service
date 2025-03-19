package com.uberkautilya.productcatalogueservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private ProductCategoryDto categoryDto;
    private String imageUrl;
    private Double price;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, ProductCategoryDto categoryDto, String imageUrl, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryDto = categoryDto;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
