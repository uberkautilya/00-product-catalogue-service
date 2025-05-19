package com.uberkautilya.productcatalogueservice.table_inheritance.mapped_super_class;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User {
    @Id
    private int id;
    private String name;
    private String email;
}
