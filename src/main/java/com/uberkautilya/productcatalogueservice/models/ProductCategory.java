package com.uberkautilya.productcatalogueservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "product_category")
public class ProductCategory extends BaseModel {
    String name;
    String description;

    @OneToMany(mappedBy = "category")
    List<Product> productList;
}
