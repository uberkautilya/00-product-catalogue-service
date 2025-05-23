package com.uberkautilya.productcatalogueservice.table_inheritance.single_table;

import jakarta.persistence.*;

@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value="0")
public class User {
    @Id
    int id;
}
