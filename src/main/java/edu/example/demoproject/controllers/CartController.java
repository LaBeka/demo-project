package edu.example.demoproject.controllers;

import edu.example.demoproject.api.CartApi;
import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CartController implements CartApi {
    private final CartService cartService;

    @Override
    public ResponseEntity getCartByUserId(Long id) {
        Optional<CartDto> cartExists = cartService.getCartByUserId(id);
        if(cartExists.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cartExists.get());
    }

    @Override
    public Long create(Long id) {
        return cartService.createCart(id);
    }

    @Override
    public void delete(Long id) {
        cartService.deleteCart(id);
    }
}
