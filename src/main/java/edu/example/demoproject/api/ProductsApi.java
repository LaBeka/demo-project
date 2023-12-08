package edu.example.demoproject.api;

import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductCriteriaDto;
import edu.example.demoproject.dtos.product.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequestMapping(ProductsApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с продуктами", description = ProductsApi.DICTS_API_PATH)
public interface ProductsApi {
    String DICTS_API_PATH = "/api/products";

    @GetMapping("/{id}")
    @Operation(summary = "Получение полной информации о продукте")
    ProductDto getFullInfoById(@PathVariable Long id);

    @GetMapping
    @Operation(summary = "Получение всех продуктов")
    List<ProductDto> getAll();

    @GetMapping("/search/title")
    @Operation(summary = "Поиск слога в описании и названии продуктов")
    List<ProductDto> findByTitle(@RequestParam(value = "title") String title);

    @GetMapping("/search/criteria")
    @Operation(summary = "Поиск продуктов по критериям(полное совпадение критерии в имени и описании, новый продукт, бренду и категориям)")
    List<ProductDto> findByCriteria(@RequestBody ProductCriteriaDto dto);

    @PostMapping()
    @Operation(summary = "Добавление нового продукта")
    @ResponseStatus(HttpStatus.OK)
    ProductDto create(@Valid @RequestBody ProductCreateDto newProductDto);

    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации(миени, описании, цена, количество, скидка, бренд и категория) о продуктe")
    @ResponseStatus(HttpStatus.OK)
    void update(@PathVariable Long id, @Valid @RequestBody ProductCreateDto newProductDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление продуктa")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long id);

}
