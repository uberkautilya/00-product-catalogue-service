package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.dtos.FakeStoreProductDto;
import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.models.ProductCategory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDto[]> productsResponseEntity = restTemplate.getForEntity(url, FakeStoreProductDto[].class);
        if (productsResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                productsResponseEntity.getBody() != null) {
            FakeStoreProductDto[] dtos = productsResponseEntity.getBody();
            return Arrays.stream(dtos).map(this::from).toList();
        }
        return List.of();
    }

    @Override
    public Product replaceProductDetails(Long id, ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";
        FakeStoreProductDto fakeStoreProductDto = from(productDto);
        ResponseEntity<FakeStoreProductDto> responseEntity = putForEntity(url, fakeStoreProductDto, FakeStoreProductDto.class, id);
        if (responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                responseEntity.hasBody()) {
            FakeStoreProductDto dto = responseEntity.getBody();
            return from(dto);
        }
        return null;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request,
                                              Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto from(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        FakeStoreProductDto dto = new FakeStoreProductDto();

        ProductCategory category = productDto.getCategory();
        dto.setCategory(null);
        if (category != null) {
            dto.setCategory(category.getName());
        }

        dto.setPrice(productDto.getPrice());
        dto.setPrice(productDto.getPrice());
        dto.setImage(productDto.getImageUrl());
        dto.setTitle(productDto.getName());
        dto.setDescription(productDto.getDescription());
        dto.setId(productDto.getId());
        return dto;
    }

    private Product from(FakeStoreProductDto dto) {
        if (dto == null) {
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
