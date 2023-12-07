package edu.example.demoproject.controllers;

import edu.example.demoproject.api.ItemApi;
import edu.example.demoproject.dtos.items.ItemCreateDto;
import edu.example.demoproject.dtos.items.ItemDto;
import edu.example.demoproject.services.ItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {
    final private ItemService itemService;

    @Override
    public ItemDto getItemByCartUserId(Long id) {
        return itemService.getById(id);
    }

    @Override
    public void addNewItem(ItemCreateDto dto) {
        itemService.addNew(dto);
    }

    @Override
    public void incrementQtyItem(Long id) {
        itemService.incrementQtyItem(id);
    }

    @Override
    public void decrementQtyItem(Long id) {
        itemService.decrement(id);
    }

    @Override
    public void delete(Long id) {
        itemService.delete(id);
    }

}
