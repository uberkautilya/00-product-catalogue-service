package com.uberkautilya.productcatalogueservice.utils;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;

public class MapperUtils {
    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());

        return product;
    }

    public static ProductDto mapToProductDto(Product product) {
        if(product == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());

        return productDto;
    }
}
