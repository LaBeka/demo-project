package edu.example.demoproject.api;

import edu.example.demoproject.dtos.picture.PictureCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;

@RequestMapping(edu.example.demoproject.api.PictureApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с продуктами", description = edu.example.demoproject.api.PictureApi.DICTS_API_PATH)
public interface PictureApi {

    String DICTS_API_PATH = "/api/products/image";

    @GetMapping("/{id}")
    @Operation(summary = "Получение фотографии продукта")
    ResponseEntity getImageByProductId(@PathVariable Long id, HttpServletRequest request) throws IOException;

    @PostMapping()
    @Operation(summary = "Добавление новой фотографии продукта")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity createPicProduct(@Valid @RequestBody PictureCreateDto pictureCreateDto, HttpServletRequest request) throws MalformedURLException;

    @PutMapping()
    @Operation(summary = "Изменение фотографии продукта")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity updatePicProductInfo(@Valid @RequestBody PictureCreateDto pictureCreateDto, HttpServletRequest request) throws IOException;

}
