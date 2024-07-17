package com.springrest.shoppingportal.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springrest.shoppingportal.entities.Products;

public interface ProductsDao extends JpaRepository<Products, Long> {
}
