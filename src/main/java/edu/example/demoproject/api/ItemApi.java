package edu.example.demoproject.api;

import edu.example.demoproject.dtos.items.ItemCreateDto;
import edu.example.demoproject.dtos.items.ItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping(edu.example.demoproject.api.ItemApi.DICTS_API_PATH)
@Tag(name = "Методы для работы сo штуками корзины", description = edu.example.demoproject.api.ItemApi.DICTS_API_PATH)
public interface ItemApi {

    String DICTS_API_PATH = "/api/items";

    @GetMapping("/{id}")
    @Operation(summary = "Получение продуктов в корзине по айди корзины/AUTH")
    ItemDto getItemByCartUserId(@PathVariable Long id);//needs to be modified later

    @PostMapping()
    @Operation(summary = "Положить в корзину новый продукт")
    @ResponseStatus(HttpStatus.OK)
    void addNewItem(@Valid @RequestBody ItemCreateDto dto);

    @PutMapping("/increment/{id}")
    @Operation(summary = "Увеличить количество продукта в корзине")
    @ResponseStatus(HttpStatus.OK)
    void incrementQtyItem(@PathVariable Long id);

    @PutMapping("/decrement/{id}")
    @Operation(summary = "Уменьшить количество продукта в корзине")
    @ResponseStatus(HttpStatus.OK)
    void decrementQtyItem(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить продукт из корзины")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long id);

}
