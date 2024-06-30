package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );

        //convert FakeStoreDto into Product
        return convertFakeStoreProducToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
            "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        //convert List of FakeStoreDto into Product.
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreProducToProduct(fakeStoreProductDto));
        }
        return products;
    }

    private Product convertFakeStoreProducToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
