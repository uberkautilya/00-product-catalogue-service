package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.models.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product replaceProductDetails(Long id, Product product);

    Product updateProductDetails(Long id, Product product);

    Boolean deleteProductDetails(Long id);
}
