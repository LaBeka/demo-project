package edu.example.demoproject.controllers;

import edu.example.demoproject.api.ItemApi;
import edu.example.demoproject.dtos.cart.CartAction;
import edu.example.demoproject.dtos.item.ItemCreateDto;
import edu.example.demoproject.dtos.item.ItemDto;
import edu.example.demoproject.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {
    final private ItemService itemService;

    @Override
    public ResponseEntity getItemsByCartId(Long cartId) {
        List<ItemDto> itemsByCartId = itemService.getItemsByCartId(cartId);//
        if(itemsByCartId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(itemsByCartId);
    }

    @Override
    public void addNewItem(ItemCreateDto dto) {
        itemService.addNewItem(dto);
    }

    @Override
    public ResponseEntity getListActions() {
        return ResponseEntity.ok().body(CartAction.values());
    }

    @Override
    public void doAction(Long productId, CartAction action) throws Exception {
        itemService.doAction(productId, action);
    }

    @Override
    public void delete(Long productId) {
        itemService.delete(productId);
    }

}
