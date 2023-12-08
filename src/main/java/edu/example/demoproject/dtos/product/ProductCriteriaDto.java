package edu.example.demoproject.dtos.product;

import lombok.Data;

@Data
public class ProductCriteriaDto {
    private String name;
    private String description;
    private Integer discount;
    private Double currentPrice;
    private boolean newProduct;
    private String brand;
    private String category;
}
