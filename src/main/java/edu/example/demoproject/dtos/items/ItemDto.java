package edu.example.demoproject.dtos.items;

import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.CartEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDto {
    private Long id;
    private Long cartEntity;
    private Long productFrontDto;
    private int qty;
}
