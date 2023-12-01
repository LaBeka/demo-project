package edu.example.demoproject.api;

import edu.example.demoproject.dtos.cart.CartDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CartApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с корзиной", description = CartApi.DICTS_API_PATH)
public interface CartApi {
    String DICTS_API_PATH = "/api/carts";

    @GetMapping("/{id}")
    @Operation(summary = "Получение корзины по айди пользователя")
    CartDto getCartByUserId(@PathVariable Long id);

    @PostMapping("/{id}")
    @Operation(summary = "Создание новой корзины по айди пользователя")
    @ResponseStatus(HttpStatus.OK)
    CartDto create(@PathVariable Long id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление корзины по айди пользователя")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long id);

}
