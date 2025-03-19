package com.uberkautilya.productcatalogueservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class can run tests on the controller endpoints, rather than its methods
 * It works at the level of the dispatcher servlet
 */
@WebMvcTest(ProductController.class)
class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean(name = "storageProductService")
    private IProductService storageProductService;

    @MockitoBean(name = "fakeStoreProductService")
    private IProductService fakeStoreProductService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void Test_GetProducts_RunSuccessful() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPhone");
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("MacBook");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        List<ProductDto> productDtoList = mapToDto(productList);

        // 1. Arrange
        when(storageProductService.getAllProducts())
                .thenReturn(productList);
        // 2. Act and Assert
        mockMvc
                .perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtoList)));
    }

    @Test
    void Test_CreateProduct_RunSuccessful() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(10L);
        productDto.setName("Product110");
        productDto.setPrice(100_000D);

        Product product = new Product();
        product.setId(10L);
        product.setName("Product110");
        product.setPrice(100_000D);

        // Here the product is generated from productDto in the controller by the mapping method.
        // The product we have here would not be the same as the one passed through to the service.

        // 1. Arrange - Use any(Product.class)
        when(storageProductService.createProduct(any(Product.class)))
                .thenReturn(product);

        // 2. Act and 3. Assert
        mockMvc
                .perform(
                        post("/products")
                                .content(objectMapper.writeValueAsString(productDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(
                        content().string(objectMapper.writeValueAsString(productDto))
                );
    }

    private List<ProductDto> mapToDto(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            if (product == null) continue;

            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            productDtoList.add(dto);
        }
        return productDtoList;
    }

}