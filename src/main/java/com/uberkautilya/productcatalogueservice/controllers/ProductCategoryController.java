package com.uberkautilya.productcatalogueservice.controllers;

import com.uberkautilya.productcatalogueservice.dtos.ProductCategoryDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/product-categories")
public class ProductCategoryController {
    //Get all
    @GetMapping
    public List<ProductCategoryDto> getAllProductCategories() {
        return null;
    }

    //Get by id
    @GetMapping
    public ProductCategoryDto getProductCategoryById(Long id) {
        return null;
    }

    //Create
    @PostMapping
    public ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        return null;
    }

    //Update
    @PatchMapping
    public ProductCategoryDto updateProductCategory(Long id, ProductCategoryDto productCategoryDto) {
        return null;
    }

    //Replace
    @PutMapping
    public ProductCategoryDto replaceProductCategory(Long id, ProductCategoryDto productCategoryDto) {
        return null;
    }

    //Delete
    @DeleteMapping
    public boolean deleteProductCategory(Long id) {
        return false;
    }
}
