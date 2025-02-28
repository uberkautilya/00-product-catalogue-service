package com.uberkautilya.productcatalogueservice.models;

import com.uberkautilya.productcatalogueservice.models.enums.State;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
    private String name;
    private String description;

    //Complex field has be expressed through a cardinality
    //We also tell JPA that the cardinality annotations on either side corresponds to a single relationship - hence, mappedBy on the side of One (ProductCategory)
    //Effectively, JPA would not create column where mappedBy is present (i.e., ProductCategory) - ignore the productList field there
    //mappedBy isn't available on the @ManyToOne annotation. Available on all others
    //cascade = CascadeType.ALL is used so that in case the category for a newly added product isn't present, a new category will be created in place
    //All changes in this category object will be carried over to the ProductCategory entry?
    //If all products of a particular category are deleted, that category would be deleted
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory category;
    private String imageUrl;
    private Double price;
    //Not to be exposed in Dto class
    private boolean isPrime;

    public Product(){
        setState(State.ACTIVE);
        setCreatedAt(new Date());
        //The same instant this object is created
        setLastUpdatedAt(getCreatedAt());
    }
}
