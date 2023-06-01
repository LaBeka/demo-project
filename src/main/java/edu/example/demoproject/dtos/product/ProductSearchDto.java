package edu.example.demoproject.dtos.product;

import edu.example.demoproject.entities.Brand;
import edu.example.demoproject.entities.Category;
import lombok.Data;

@Data
public class ProductSearchDto {
    private String name;
    private String description;
    private boolean loweredPrice;
    private boolean newProduct;
    private Brand brand;
    private Category category;

}
