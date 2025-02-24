package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long id);

    List<Product> getAllProducts();

    Product replaceProductDetails(Long id, ProductDto productDto);
}
