package com.uberkautilya.productcatalogueservice.utils;

import com.uberkautilya.productcatalogueservice.dtos.ProductCategoryDto;
import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.models.ProductCategory;

public class MapperUtils {
    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());

        ProductCategoryDto categoryDto = productDto.getCategoryDto();
        if (categoryDto != null) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(categoryDto.getId());
            productCategory.setName(categoryDto.getName());
            productCategory.setDescription(categoryDto.getDescription());
            product.setCategory(productCategory);
        }
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
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());

        ProductCategory category = product.getCategory();
        if (category != null) {
            ProductCategoryDto productCategoryDto = new ProductCategoryDto();
            productCategoryDto.setId(category.getId());
            productCategoryDto.setName(category.getName());
            productCategoryDto.setDescription(category.getDescription());
            productDto.setCategoryDto(productCategoryDto);
        }
        return productDto;
    }
}
