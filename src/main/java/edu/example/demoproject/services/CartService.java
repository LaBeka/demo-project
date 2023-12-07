package edu.example.demoproject.services;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.entities.CartEntity;
import edu.example.demoproject.mappers.CartMapper;
import edu.example.demoproject.repos.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public List<CartDto> getCartByUserId(Long id) {
        return cartRepository.getCart(id);
    }

    @Transactional
    public CartDto createCart(Long id) {
        List<CartDto> cartExists = getCartByUserId(id);
        if(!cartExists.isEmpty()){
            return cartExists.get(0);//hard code needs to be fixed later
        }

        CartDto dto = new CartDto(null, id);
        CartEntity entity = cartMapper.dtoToEntity(dto);
        cartRepository.persist(entity);
        return getCartByUserId(id).get(0);//нужно исправить в будущем, чтобы возвращал только один актуальную корзину, этого чела.
    }

    @Transactional
    public void deleteCart(Long id) {
        cartRepository.delete(id);
    }
}
