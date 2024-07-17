package com.springrest.shoppingportal.services;

import com.springrest.shoppingportal.entities.Products;

import java.util.List;

public interface ProductsService {
	
    List<Products> getProducts();
    
    Products getProduct(long productId);
    
    Products updateProducts(Products product);
    
    void deleteProducts(long productId);

	Products addProduct(Products product);
    
}