package com.uberkautilya.productcatalogueservice.dtos;

import com.uberkautilya.productcatalogueservice.models.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private ProductCategory category;
    private String imageUrl;
    private Double price;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, ProductCategory category, String imageUrl, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
