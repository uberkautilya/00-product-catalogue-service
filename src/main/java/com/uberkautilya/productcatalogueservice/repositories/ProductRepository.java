package com.uberkautilya.productcatalogueservice.repositories;

import com.uberkautilya.productcatalogueservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is Spring Data JPA abstraction.
 * With just JPA, we would call persist method on an entityManager object by specifying the type as a parameter.
 * With Spring Data JPA, which is another layer of abstraction over the simple JPA,
 * where we have underlying method naming conventions and default ones that are managed with hibernate.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from products p where p.id = :id")
    Product findProduct(@Param("id") Long productId);
}
