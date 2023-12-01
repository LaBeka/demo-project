package edu.example.demoproject.services;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.repos.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public CartDto getCartByUserId(Long id) {
        return cartRepository.getCart(id);
    }

    @Transactional
    public CartDto createCart(Long id) {
        cartRepository.create(id);
        return getCartByUserId(id);
    }

    @Transactional
    public void deleteCart(Long id) {
        cartRepository.delete(id);
    }
}
