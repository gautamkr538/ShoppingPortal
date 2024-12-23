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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Products API", description = "API for Products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/home")
    @Operation(summary = "Say Hello", description = "Returns a greeting message")
    public String home() {
        return "This is Home Page";
    }

    @GetMapping("/products")
    @Operation(summary = "Calling Products", description = "Returns a Products list")
    public List<Products> getAllProducts() {
        return this.productsService.getProducts();
    }

    @GetMapping("/products/{productId}")
    @Operation(summary = "Product by Id", description = "Returns a Product with that Id")
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
    @Operation(summary = "Adding Product", description = "Adding Product")
    public ResponseEntity<Products> addProduct(@RequestBody Products product) {
        try {
            Products addedProduct = this.productsService.addProduct(product);
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products")
    @Operation(summary = "Update Product", description = "Update that Prouct")
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
    @Operation(summary = "Delete Product", description = "Delete the Product by Id")
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
