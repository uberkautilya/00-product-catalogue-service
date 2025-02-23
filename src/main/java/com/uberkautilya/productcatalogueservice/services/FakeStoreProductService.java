package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.dtos.FakeStoreProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.models.ProductCategory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService {
    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";
        //Todo: id is the argument to the restTemplate method call
        ResponseEntity<FakeStoreProductDto> dtoResponseEntity = restTemplate.getForEntity(url, FakeStoreProductDto.class, id);
        if (dtoResponseEntity.getBody() != null &&
                dtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            FakeStoreProductDto dto = dtoResponseEntity.getBody();
            return from(dto);
        }
        return null;
    }

    private Product from(FakeStoreProductDto dto) {
        if(dto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getTitle());
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(dto.getCategory());
        product.setCategory(productCategory);
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImage());
        return product;
    }
}
