package com.scaler.productservice.models;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private Double price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    private String image;

}
/*
Product     Category
1           1
M           1
M:1
*/

