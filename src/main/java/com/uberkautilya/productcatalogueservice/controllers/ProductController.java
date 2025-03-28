package com.uberkautilya.productcatalogueservice.controllers;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.services.IProductService;
import com.uberkautilya.productcatalogueservice.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {
    private final IProductService storageProductService;
    private final IProductService fakeStoreProductService;

    public ProductController(
            @Qualifier(value = "storageProductService") IProductService storageProductService,
            @Qualifier(value = "fakeStoreProductService") IProductService fakeStoreProductService) {
        this.storageProductService = storageProductService;
        this.fakeStoreProductService = fakeStoreProductService;
    }

    @PostMapping
    public ProductDto saveProductDetails(@RequestBody ProductDto productDto) {
        Product product = MapperUtils.mapToProduct(productDto);
        Product savedProduct = storageProductService.createProduct(product);
        return MapperUtils.mapToProductDto(savedProduct);
    }

    @GetMapping("/{id}")
    public ProductDto getProductDetails(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid product id");
        }
        Product productById = storageProductService.getProductById(id);
        return MapperUtils.mapToProductDto(productById);
    }

    @GetMapping
    public List<ProductDto> getAllProductDetails() {
        List<Product> productList = storageProductService.getAllProducts();
        return productList.stream().map(MapperUtils::mapToProductDto).toList();
    }

    @PutMapping("/{id}")
    public ProductDto replaceProductDetails(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product requestProduct = MapperUtils.mapToProduct(productDto);
        Product product = storageProductService.replaceProductDetails(id, requestProduct);
        if (product == null) {
            return null;
        }
        return MapperUtils.mapToProductDto(product);
    }

    @PatchMapping("/{id}")
    public ProductDto patchProductDetails(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product requestProduct = MapperUtils.mapToProduct(productDto);
        Product product = storageProductService.updateProductDetails(id, requestProduct);
        if (product == null) {
            return null;
        }
        return MapperUtils.mapToProductDto(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductDetails(@PathVariable Long id) {

        //For a resource that exists, 204 is returned, while for subsequent attempts 404 is returned
        Boolean isDeleteSuccessful = storageProductService.deleteProductDetails(id);
        if (!isDeleteSuccessful) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
