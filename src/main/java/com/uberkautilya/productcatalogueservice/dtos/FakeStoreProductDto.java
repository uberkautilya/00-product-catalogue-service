package com.uberkautilya.productcatalogueservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
    //Rating is not used by us. Skip this field, which is present in the API response
}
