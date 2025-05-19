package com.uberkautilya.productcatalogueservice.table_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//In single table, there is only a single table creation - st_ta isn't created.
@Entity(name = "st_ta")
@DiscriminatorValue(value="1")
public class TA extends User {
    Integer queryCount;
}
