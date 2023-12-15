package edu.example.demoproject.api;


import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequestMapping(edu.example.demoproject.api.ImageApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с images", description = edu.example.demoproject.api.ImageApi.DICTS_API_PATH)
public interface ImageApi {
    String DICTS_API_PATH = "/api/image";

    @PostMapping(value = "/add/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление новой фотографии продукта по айди продукта")
    @ResponseStatus(HttpStatus.OK)
    void createImage(
            @Valid @RequestPart("file") MultipartFile file,
            @PathVariable Long productId) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    @GetMapping("/readFile/{id}")
    @Operation(summary = "Показать фото по айди Image")
    ResponseEntity showImageByItsId(@PathVariable Long id) throws IOException;


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить фотографию продукта по айди PictureEntity")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long id);


    @PutMapping("/update/{id}")
    @Operation(summary = "Поменять фотографию продукта по айди PictureEntity")
    @ResponseStatus(HttpStatus.OK)
    void updatePicProductInfo(
            @Valid @RequestPart("file")MultipartFile file,
            @PathVariable Long id) throws IOException;


    @GetMapping("/getListImages/{productId}")
    @Operation(summary = "Получение список энтити фотографии по айди продукта")
    ResponseEntity getListImages(@PathVariable Long productId) throws IOException;

}