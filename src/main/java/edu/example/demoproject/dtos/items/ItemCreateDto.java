package edu.example.demoproject.dtos.items;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ItemCreateDto {

    @NotNull(message="Cart id is null")
    private Long cartId;

    @NotNull(message="Product id is null")
    private Long productId;

    private Integer qty;
}
