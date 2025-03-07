package com.uberkautilya.productcatalogueservice.table_inheritance.joined_class;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "j_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    String company;
}
