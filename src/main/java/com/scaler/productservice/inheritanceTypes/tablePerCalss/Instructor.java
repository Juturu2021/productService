package com.scaler.productservice.inheritanceTypes.tablePerCalss;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_instructors")
public class Instructor extends User {
    private String instructorName;
}
