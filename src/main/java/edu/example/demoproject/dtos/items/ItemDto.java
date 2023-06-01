package edu.example.demoproject.dtos.items;

import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.Cart;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDto {
    private Long id;
    private Cart cart;
    private ProductDto productDto;
    private int qty;
}
