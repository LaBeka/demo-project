package edu.example.demoproject.dtos.product;

import edu.example.demoproject.dtos.brand.BrandDto;
import edu.example.demoproject.dtos.category.CategoryDto;
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
    private String currentPrice;
    private String newProduct;
    private BrandDto brand;
    private CategoryDto category;
}
