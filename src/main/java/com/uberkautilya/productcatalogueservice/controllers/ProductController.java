package com.uberkautilya.productcatalogueservice.controllers;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.services.IProductService;
import com.uberkautilya.productcatalogueservice.utils.MapperUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto saveProductDetails(@RequestBody ProductDto productDto) {
        MapperUtils.mapToProduct(productDto);
        return null;
    }

    @GetMapping("/{id}")
    public ProductDto getProductDetails(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        return MapperUtils.mapToProductDto(productById);
    }

    @GetMapping
    public List<ProductDto> getAllProductDetails() {
        List<Product> productList = productService.getAllProducts();
        return productList.stream().map(MapperUtils::mapToProductDto).toList();
    }

    @PutMapping("/{id}")
    public ProductDto replaceProductDetails(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = productService.replaceProductDetails(id, productDto);
        if(product == null) {
            return null;
        }
        return MapperUtils.mapToProductDto(product);
    }

    @PatchMapping("/{id}")
    public ProductDto patchProductDetails(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productDto;
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductDetails(@PathVariable Long id) {
        return false;
    }
}
