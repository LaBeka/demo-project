package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.dtos.items.ItemDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemMapper {
    private ProductMapper productMapper;
    private CartMapper cartMapper;
    public ItemDto buildItem(Item item){
        ProductDto productDto = this.productMapper.buildProduct(
                item.getProduct());
        CartDto cartDto = this.cartMapper.buildCart(item.getCart());

        return ItemDto.builder()
                .id(item.getId())
                .cartDto(cartDto)
                .productDto(productDto)
                .qty(item.getQty())
                .build();
    }
}
