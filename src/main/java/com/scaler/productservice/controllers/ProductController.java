package com.scaler.productservice.controllers;
import java.util.*;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

//    private final SelfPorductService selfPorductService;
    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService")
                             ProductService productService){

        this.productService = productService;
    }
    ResponseEntity<Product> responseEntity = null;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(
                productService.getAllProduct(),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/category/{name}")
    public List<Product> getCategoryByName(@PathVariable("name") String name){
        return productService.getSingleCategory(name);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){

        return productService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    @PostMapping()
    public ResponseEntity<Product> postProduct(@RequestBody Product product){
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                productService.addNewProduct(product),
                HttpStatus.CREATED
        );
        return responseEntity;
    }
}
