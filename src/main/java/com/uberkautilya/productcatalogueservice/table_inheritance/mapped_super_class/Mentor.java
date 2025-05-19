package com.uberkautilya.productcatalogueservice.table_inheritance.mapped_super_class;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mentor extends User {
    String companyName;
}
