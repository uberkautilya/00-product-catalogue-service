package com.uberkautilya.productcatalogueservice.controllers;

import com.uberkautilya.productcatalogueservice.dtos.ProductDto;
import com.uberkautilya.productcatalogueservice.models.Product;
import com.uberkautilya.productcatalogueservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockitoBean(name = "storageProductService")
    private IProductService productServiceMockitoBean;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void testGetProductDetailsById_WithValidProductId_RunSuccessfully() {
        //1. Arrange
        Long id = 3L;
        String productName = "iPhone";
        double productPrice = 100000.00;
        Product product = getProduct(id, productName, productPrice);
        when(productServiceMockitoBean.getProductById(id))
                .thenReturn(product);
        //2. Act
        ProductDto productDetails = productController.getProductDetails(id);
        //3. Assert
        assertNotNull(productDetails);
        assertEquals(id, productDetails.getId());
        assertEquals(productName, productDetails.getName());
        assertEquals(productPrice, productDetails.getPrice());
    }

    @Test
    public void testGetProductDetailsById_WithNegativeProductId_ThrowsIllegalArgumentException() {
        //1. Arrange
        Long id = -1L;
        //2. Act and 3. Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> productController.getProductDetails(id), "Invalid product id"
        );
    }

    @Test
    public void testGetProductDetailsById_WithNegativeProductId_ControllerAdviseResponse() {
        //1. Arrange
        Long id = -1L;
        //2. Act and 3. Assert
        IllegalArgumentException invalidProductId = assertThrows(
                IllegalArgumentException.class,
                () -> productController.getProductDetails(id), "Invalid product id"
        );
        assertEquals("Invalid product id", invalidProductId.getMessage());
        // verify is used to assert the calls on any mockito beans
        verify(productServiceMockitoBean, times(0)).getProductById(id);
    }

    @Test
    public void testGetProductDetailsById_WithValidIdArgumentCaptor_Success() {
        // 1. Arrange
        Long id = 1L;
        String productName = "iPhone";
        double productPrice = 100000.00;
        Product product = getProduct(id, productName, productPrice);
        when(productServiceMockitoBean.getProductById(id))
                .thenReturn(product);
        // 2. Act
        productController.getProductDetails(id);
        // 3. Assert: Todo: Usage of Captor - learn this ✅
        // verify used to assert calls on mockito beans. idCaptor captures the true value that is passed
        // In short, asserts there is a method call on mockito bean, and idCaptor captures this value
        verify(productServiceMockitoBean).getProductById(idCaptor.capture());
        assertEquals(id, idCaptor.getValue());
    }

    private static Product getProduct(Long id, String productName, double productPrice) {
        Product product = new Product();
        product.setId(id);
        product.setName(productName);
        product.setPrice(productPrice);
        return product;
    }
}