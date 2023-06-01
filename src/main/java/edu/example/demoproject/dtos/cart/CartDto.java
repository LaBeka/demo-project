package edu.example.demoproject.dtos.cart;

import edu.example.demoproject.dtos.items.ItemDto;
import edu.example.demoproject.dtos.user.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CartDto {
    private UserDto userDto;
    private List<ItemDto> items;
}
