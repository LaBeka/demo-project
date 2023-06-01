package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.dtos.items.ItemDto;
import edu.example.demoproject.dtos.user.UserDto;
import edu.example.demoproject.entities.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class CartMapper{
    private UserMapper userMapper;
    private ItemMapper itemMapper;
    public CartDto buildCart(Cart cart){

        UserDto userDto = this.userMapper.buildUser(cart.getUser());
        List<ItemDto> itemDto = cart.getItems().stream()
                .map(i -> this.itemMapper.buildItem(i))
                .collect(Collectors.toList());

        return CartDto.builder()
                .userDto(userDto)
                .items(itemDto)
                .build();
    }
}