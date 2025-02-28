package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDto productDto);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product replaceProductDetails(Long id, ProductDto productDto);

    Product updateProductDetails(Long id, ProductDto productDto);

    Product deleteProductDetails(Long id);
}
