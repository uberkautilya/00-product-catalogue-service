package com.uberkautilya.productcatalogueservice.controllers;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.utils.MapperUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/products")
public class ProductController {

    @PostMapping
    public ProductDto saveProductDetails(@RequestBody ProductDto productDto) {
        MapperUtils.mapToProduct(productDto);
        return null;
    }

    @GetMapping("/{id}")
    public ProductDto getProductDetails(@PathVariable Long id) {
        ProductDto productDto = MapperUtils.mapToProductDto((Product) null);
        return productDto;
    }

    @GetMapping
    public List<ProductDto> getAllProductDetails() {
        return null;
    }

    @PutMapping("/{id}")
    public ProductDto updateProductDetails(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productDto;
    }

    @PatchMapping("/{id}")
    public ProductDto patchProductDetails(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productDto;
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductDetails(@PathVariable Long id) {
        return false;
    }
}
