package com.uberkautilya.productcatalogueservice.models;

import com.uberkautilya.productcatalogueservice.models.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * We won't make an object of this class, so we can make it abstract.
 */
@Getter
@Setter
public abstract class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    // For soft delete
    private State state;
}
