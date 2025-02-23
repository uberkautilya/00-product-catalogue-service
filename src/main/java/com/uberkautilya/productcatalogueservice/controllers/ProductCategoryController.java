package com.uberkautilya.productcatalogueservice.controllers;

import com.uberkautilya.productcatalogueservice.dtos.ProductCategoryDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/product-categories")
public class ProductCategoryController {
    @GetMapping
    public List<ProductCategoryDto> getAllProductCategories() {
        return null;
    }

    @GetMapping("/{id}")
    public ProductCategoryDto getProductCategoryById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ProductCategoryDto saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return null;
    }

    @PatchMapping("/{id}")
    public ProductCategoryDto updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDto productCategoryDto) {
        return null;
    }

    @PutMapping("/{id}")
    public ProductCategoryDto replaceProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDto productCategoryDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductCategory(@PathVariable Long id) {
        return false;
    }
}
