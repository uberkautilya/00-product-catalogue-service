package com.uberkautilya.productcatalogueservice.table_inheritance.table_per_class;

import jakarta.persistence.Entity;

@Entity(name = "tpc_instructor")
public class Instructor extends User {
    String company;
}
