package com.uberkautilya.productcatalogueservice.table_inheritance.mapped_super_class;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TA extends User {
    private int noOfHours;
}
