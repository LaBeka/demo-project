package edu.example.demoproject.dtos.cart;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CartDto {
    private Long id;
    private Long userId;
}
