package edu.example.demoproject.dtos.items;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.dtos.product.ProductDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDto {
    private Long id;
    private CartDto cartDto;
    private ProductDto productDto;
    private int qty;
}
