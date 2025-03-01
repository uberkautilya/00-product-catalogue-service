package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "storageProductService") //The value here is redundant, as this is the default naming for this bean anyway
//@Primary //When only this implementation is to be active. To use both implementations simultaneously, use @Qualifier
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
        Optional<Product> productByIdOptional = productRepository.findById(id);
        return productByIdOptional.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product replaceProductDetails(Long id, Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProductDetails(Long id, Product product) {
        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProductDetails(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        if (productById.isEmpty()) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
