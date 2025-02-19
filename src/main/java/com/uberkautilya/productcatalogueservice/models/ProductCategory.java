package com.uberkautilya.productcatalogueservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategory extends BaseModel {
    String name;
    String description;
    List<Product> productList;
}
