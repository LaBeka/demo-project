package edu.example.demoproject.controllers;

import edu.example.demoproject.api.CartApi;
import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController implements CartApi {
    private final CartService cartService;

    @Override
    public List<CartDto> getCartByUserId(Long id) {
        return cartService.getCartByUserId(id);
    }

    @Override
    public CartDto create(Long id) {
        return cartService.createCart(id);
    }

    @Override
    public void delete(Long id) {
        cartService.deleteCart(id);
    }
}
