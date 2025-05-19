package com.uberkautilya.productcatalogueservice.table_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//In single table, there is only a single table creation - st_instructor isn't created.
@Entity(name = "st_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    String company;
}
