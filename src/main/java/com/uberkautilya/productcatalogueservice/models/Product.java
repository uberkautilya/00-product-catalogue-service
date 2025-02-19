package com.uberkautilya.productcatalogueservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String description;
    private ProductCategory category;
    private String imageUrl;
    private Double price;
    //Not to be exposed in Dto class
    private boolean isPrime;
}
