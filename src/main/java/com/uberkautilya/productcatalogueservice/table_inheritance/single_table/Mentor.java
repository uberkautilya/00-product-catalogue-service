package com.uberkautilya.productcatalogueservice.table_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//In single table, there is only a single table creation - st_mentor isn't created.
@Entity(name = "st_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    Double ratings;
}
