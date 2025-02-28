package com.uberkautilya.productcatalogueservice.models;

import com.uberkautilya.productcatalogueservice.models.enums.State;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * We won't make an object of this class, so we can make it abstract.
 */
@Getter
@Setter
@MappedSuperclass //All added as fields in the child entity classes
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    // For soft delete
    private State state;
}
