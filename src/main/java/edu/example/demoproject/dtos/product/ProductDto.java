package edu.example.demoproject.dtos.product;

import edu.example.demoproject.entities.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Double initialPrice;
    private Integer discount;
    private boolean newProduct;
    private Brand brand;
    private Category category;
}
