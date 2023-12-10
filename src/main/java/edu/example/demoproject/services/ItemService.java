package edu.example.demoproject.services;

import edu.example.demoproject.dtos.items.ItemCreateDto;
import edu.example.demoproject.dtos.items.ItemDto;
import edu.example.demoproject.entities.ItemEntity;
import edu.example.demoproject.mappers.ItemMapper;
import edu.example.demoproject.repos.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ItemDto> getItemsByCartId(Long cartId) {//cartId вытащен из cart-контроллер
        return itemRepository.getItemsByCartId(cartId);
    }

    @Transactional
    public void addNewItem(ItemCreateDto dto) {//при нажатии на кнопку добавить рядом с продуктом-может заработать кнопка плюсик
        Optional<ItemDto> ifItemExists = checkIfItemExists(dto);

        if (ifItemExists.isEmpty()) {
            ItemEntity entity = itemMapper.dtoToEntity(dto);
            itemRepository.persist(entity);
        } else {
            incrementQtyItem(ifItemExists.get().getProductId());
        }
    }

    @Transactional
    public void incrementQtyItem(Long productId) {//при нажатии на кнопку плюсик рядом с продуктом
        ItemDto dto = getItemByProductId(productId);
        itemRepository.incrementQty(dto);
    }

    @Transactional
    public void decrementQtyItem(Long productId) {
        ItemDto dto = getItemByProductId(productId);
        itemRepository.decrementQty(dto);
        removeItemQtyNull(dto);
    }

    @Transactional
    public void delete(Long productId) {
        itemRepository.delete(productId);
    }

    private ItemDto getItemByProductId(Long id) {
        return itemRepository.getItemByItsProductId(id);
    }

    private Optional<ItemDto> checkIfItemExists(ItemCreateDto dto) {
        return itemRepository.ifItemInCartExists(dto);
    }

    @Transactional
    public void removeItemQtyNull(ItemDto dto) {
        itemRepository.deleteIfQtyNull(dto);
    }

}
