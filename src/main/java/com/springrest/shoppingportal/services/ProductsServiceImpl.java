package com.springrest.shoppingportal.services;


import com.springrest.shoppingportal.Dao.ProductsDao;
import com.springrest.shoppingportal.entities.Products;
import com.springrest.shoppingportal.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsDao productsDao;

    @Autowired
    public ProductsServiceImpl(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    @Override
    public List<Products> getProducts() {
        return productsDao.findAll();
    }

    @Override
    public Products getProduct(long productId) {
        return productsDao.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public Products addProduct(Products product) {
        return productsDao.save(product);
    }


    @Override
    public Products updateProducts(Products product) {
        if (!productsDao.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found with id: " + product.getId());
        }
        return productsDao.save(product);
    }

    @Override
    public void deleteProducts(long productId) {
        Products entity = productsDao.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        productsDao.delete(entity);
    }
}
