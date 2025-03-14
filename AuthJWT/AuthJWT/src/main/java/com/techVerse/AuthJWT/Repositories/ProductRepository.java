package com.techVerse.AuthJWT.Repositories;

import com.techVerse.AuthJWT.Entities.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
