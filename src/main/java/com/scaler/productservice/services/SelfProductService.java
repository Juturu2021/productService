package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
//        return List.of();
    }

    @Override
    public List<Product> getSingleCategory(String name) {
        Category category = new Category();
        return productRepository.findAllProductsByCategoryName(category.getName());
    }

    @Override
    public List<String> getAllCategories() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        Product productinDB = optionalProduct.get();
        if(product.getTitle() != null){
            productinDB.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productinDB.setPrice(product.getPrice());
        }
        if(product.getDescription() != null){
            productinDB.setDescription(product.getDescription());
        }
        return productRepository.save(productinDB);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        Product productinDB = optionalProduct.get();

        if(product.getTitle() != null){
            productinDB.setTitle(product.getTitle());
        }
        if(product.getDescription() != null){
            productinDB.setDescription(product.getDescription());
        }
        if(product.getPrice() != null){
            productinDB.setPrice(product.getPrice());
        }
        if(product.getCategory() != null){
            productinDB.setCategory(product.getCategory());
        }
        if(product.getImage() != null){
            productinDB.setImage(product.getImage());
        }
        return productRepository.save(productinDB);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();
//        if(category.getId() == null){
//            category = categoryRepository.save(category);
//            product.setCategory(category);
//        }
        return productRepository.save(product);
    }
}
