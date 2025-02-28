package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductService {
    private final ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        //If a product with the same Id existed earlier, then we would not have that information at the controller level
        Optional<Product> productById = productRepository.findById(product.getId());
        return productById.orElseGet(() -> productRepository.save(product));
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
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
    public Product deleteProductDetails(Long id) {
        return null;
    }
}
