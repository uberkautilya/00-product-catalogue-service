package com.uberkautilya.productcatalogueservice.services;

import com.uberkautilya.productcatalogueservice.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class StorageProductServiceTest {
    // No context set up. Hence, beans wouldn't work. A simple mock object is created here.
    StorageProductService storageProductService = Mockito.mock(StorageProductService.class);

    @Test
    public void testLogic() {
        assertEquals(5 + 6, 11);
        System.out.println("This is a Unit Test");
    }

    @Test
    public void testStorageProductService() {
        // 1. Arrange
        when(storageProductService.getProductById(1L))
                .thenReturn(new Product());
        // 2. Act
        Product product = storageProductService.getProductById(1L);
        // 3. Assert
        assertNotNull(product);
    }

}