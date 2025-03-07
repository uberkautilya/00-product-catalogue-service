package com.uberkautilya.productcatalogueservice.table_inheritance.table_per_class;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User {
    Integer queryCount;
}
