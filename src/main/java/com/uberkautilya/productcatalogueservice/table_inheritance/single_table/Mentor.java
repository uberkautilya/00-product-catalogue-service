package com.uberkautilya.productcatalogueservice.table_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_mentor")
@DiscriminatorValue(value = "Mentor")
public class Mentor extends User {
    Double ratings;
}
