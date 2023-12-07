package edu.example.demoproject.dtos.cart;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CartDto {
    private Long id;
    private Long userId;
}
