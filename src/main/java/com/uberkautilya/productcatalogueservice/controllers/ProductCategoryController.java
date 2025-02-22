package com.uberkautilya.productcatalogueservice.controllers;

import com.uberkautilya.productcatalogueservice.dtos.ProductCategoryDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/product-categories")
public class ProductCategoryController {
    @GetMapping
    public List<ProductCategoryDto> getAllProductCategories() {
        return null;
    }

    @GetMapping
    public ProductCategoryDto getProductCategoryById(Long id) {
        return null;
    }

    @PostMapping
    public ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        return null;
    }

    @PatchMapping
    public ProductCategoryDto updateProductCategory(Long id, ProductCategoryDto productCategoryDto) {
        return null;
    }

    @PutMapping
    public ProductCategoryDto replaceProductCategory(Long id, ProductCategoryDto productCategoryDto) {
        return null;
    }

    @DeleteMapping
    public boolean deleteProductCategory(Long id) {
        return false;
    }
}
