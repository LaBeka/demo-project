package edu.example.demoproject.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double initialPrice;
    private Integer discount;
    private Double currentPrice;
    private boolean newProduct;
    private String brand;
    private String category;
}
