package com.uberkautilya.productcatalogueservice.table_inheritance.table_per_class;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User {
    Double ratings;
}
