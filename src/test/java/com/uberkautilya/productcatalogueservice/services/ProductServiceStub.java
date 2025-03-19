package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.models.Product;

import java.util.List;

public class ProductServiceStub implements IProductService {
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProductDetails(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProductDetails(Long id, Product product) {
        return null;
    }

    @Override
    public Boolean deleteProductDetails(Long id) {
        return null;
    }
}
