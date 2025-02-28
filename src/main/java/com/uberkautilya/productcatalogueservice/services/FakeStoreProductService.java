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
    public Product createProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products";

        FakeStoreProductDto fakeStoreProductDto = from(productDto);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(url, fakeStoreProductDto, FakeStoreProductDto.class);
        if (responseEntity.getBody() != null &&
                responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(201))) {
            FakeStoreProductDto dto = responseEntity.getBody();
            return from(dto);
        }
        return null;
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
        ResponseEntity<FakeStoreProductDto> responseEntity = executeForEntity(url, fakeStoreProductDto, FakeStoreProductDto.class, HttpMethod.PUT, id);
        if (responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                responseEntity.hasBody()) {
            FakeStoreProductDto dto = responseEntity.getBody();
            return from(dto);
        }
        return null;
    }

    @Override
    public Product updateProductDetails(Long id, ProductDto productDto) {
        FakeStoreProductDto fakeStoreProductDto = from(productDto);
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";

        //We go to RestTemplate, find postForEntity and adapt the exchange method call for PATCH
        ResponseEntity<FakeStoreProductDto> responseEntity = executeForEntity(url, fakeStoreProductDto, FakeStoreProductDto.class, HttpMethod.PATCH, id);

        if (responseEntity != null && HttpStatusCode.valueOf(200).equals(responseEntity.getStatusCode())) {
            FakeStoreProductDto dto = responseEntity.getBody();
            return from(dto);
        }
        return null;
    }

    @Override
    public Product deleteProductDetails(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";
        Product productById = getProductById(id);
        if (productById == null) {
            return null;
        }

        restTemplate.delete(url, id);
        return productById;
    }

    /**
     * We go to RestTemplate, find postForEntity and adapt the execute method call for PATCH
     */
    public <T> ResponseEntity<T> executeForEntity(String url, @Nullable Object requestObject,
                                                  Class<T> responseType, HttpMethod httpMethod, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestObject, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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
