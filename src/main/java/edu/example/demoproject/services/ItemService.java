package edu.example.demoproject.services;

import edu.example.demoproject.dtos.items.ItemCreateDto;
import edu.example.demoproject.dtos.items.ItemDto;
import edu.example.demoproject.entities.ItemEntity;
import edu.example.demoproject.mappers.ItemMapper;
import edu.example.demoproject.repos.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemDto getById(Long id) {
        return itemRepository.getById(id);
    }

    @Transactional
    public void addNew(ItemCreateDto dto) {
        Optional<ItemDto> ifItemExists = checkIfItemExists(dto);

        if (ifItemExists.isEmpty()) {
            ItemEntity entity = itemMapper.dtoToEntity(dto);
            itemRepository.persist(entity);
        } else {
            incrementQtyItem(ifItemExists.get().getId());
        }
    }

    @Transactional
    public void incrementQtyItem(Long id) {
        ItemDto dto = getById(id);
        itemRepository.incrementQty(dto);
    }

    @Transactional
    public void decrement(Long id) {
        ItemDto dto = getById(id);
        itemRepository.decrementQty(dto);
        removeItemQtyNull(dto);
    }

    @Transactional
    public void delete(Long id) {
        itemRepository.delete(id);
    }

    private Optional<ItemDto> checkIfItemExists(ItemCreateDto dto) {
        return itemRepository.ifItemInCartExists(dto);
    }

    @Transactional
    public void removeItemQtyNull(ItemDto dto) {
        itemRepository.deleteIfQtyNull(dto);
    }

}
