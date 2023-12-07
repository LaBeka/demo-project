package edu.example.demoproject.dtos.items;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer qty;
}
