package com.scaler.productservice.services;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProduct();
    List<Product> getSingleCategory(String name);
    List<String> getAllCategories();
    Product updateProduct(long id, Product product);
    Product replaceProduct(long id, Product product);
    Product deleteProduct(long id);
    Product addNewProduct(Product product);
}
