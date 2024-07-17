package com.springrest.shoppingportal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.shoppingportal.entities.Products;
import com.springrest.shoppingportal.services.ProductsService;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/home")
    public String home() {
        return "This is Home Page";
    }

    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return this.productsService.getProducts();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Products> getProduct(@PathVariable String productId) {
        try {
            Products product = this.productsService.getProduct(Long.parseLong(productId));
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Products> addProduct(@RequestBody Products product) {
        try {
            Products addedProduct = this.productsService.addProduct(product);
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products")
    public ResponseEntity<Products> updateProduct(@RequestBody Products product) {
        try {
            Products updatedProduct = this.productsService.updateProducts(product);
            if (updatedProduct != null) {
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String productId) {
        try {
            this.productsService.deleteProducts(Long.parseLong(productId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
