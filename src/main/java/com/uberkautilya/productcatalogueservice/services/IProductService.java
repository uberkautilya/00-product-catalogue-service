package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.models.Product;

public interface IProductService {
    public Product getProductById(Long id);
}
