package com.uberkautilya.productcatalogueservice.table_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_ta")
@DiscriminatorValue(value="TA")
public class TA extends User {
    Integer queryCount;
}
