package com.scaler.productservice.exceptions;


public class ProductNotFoundException extends RuntimeException{
    private Long productId;
    public ProductNotFoundException(Long productId){
        super("Product with id " + productId + " doesn't exist");
        this.productId = productId;
    }

    public Long getProdcut() {
        return productId;

    }
}
