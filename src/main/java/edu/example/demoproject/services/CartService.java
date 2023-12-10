package edu.example.demoproject.services;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.entities.CartEntity;
import edu.example.demoproject.mappers.CartMapper;
import edu.example.demoproject.repos.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public Optional<CartDto> getCartByUserId(Long id) {
        return cartRepository.getCart(id);
    }

    @Transactional
    public Long createCart(Long id) {
        Optional<CartDto> cartExists = getCartByUserId(id);
        if(cartExists.isPresent()){
            return cartExists.get().getId();//позволит не создавать вторую карту у данного пользователя
        }

        CartDto dto = new CartDto(null, id);
        CartEntity entity = cartMapper.dtoToEntity(dto);
        cartRepository.persist(entity);
        return entity.getId();//send back only id of cart to put new/notnew items
    }

    @Transactional
    public void deleteCart(Long id) {
        cartRepository.delete(id);
    }
}
