package edu.example.demoproject.api;

import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(ProductsApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с продуктами", description = ProductsApi.DICTS_API_PATH)
public interface ProductsApi {
    String DICTS_API_PATH = "/api/products";

    @GetMapping("/{id}")
    @Operation(summary = "Получение полной информации о product")
    ProductDto getFullInfoById(@PathVariable Long id);

    @GetMapping
    @Operation(summary = "Получение всех товаров")
    List<ProductDto> getAllProducts();

    @GetMapping("/search")
    @Operation(summary = "Поиск по продуктаm")
    List<ProductDto> find(@RequestParam(value = "title") String title);

    @PostMapping()
    @Operation(summary = "Добавление нового продукта")
    @ResponseStatus(HttpStatus.OK)
    ProductDto createProduct(@Valid @RequestBody ProductCreateDto newProductDto);

    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации о продукта")
    @ResponseStatus(HttpStatus.OK)
    void updateProductInfo(@PathVariable Long id, @Valid @RequestBody ProductCreateDto newProductDto);

    
}
