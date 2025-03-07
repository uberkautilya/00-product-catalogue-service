package com.uberkautilya.productcatalogueservice.table_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "tpc_instructor")
@DiscriminatorValue(value = "Instructor")
public class Instructor extends User {
    String company;
}
