package edu.example.demoproject.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(edu.example.demoproject.api.ImageApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с images", description = edu.example.demoproject.api.ImageApi.DICTS_API_PATH)
public interface ImageApi {
    String DICTS_API_PATH = "/api/pic";

    @PostMapping(value = "/add/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление новой фотографии продукта по айди продукта")
    @ResponseStatus(HttpStatus.OK)
    void createImage(
            @Valid @RequestPart("file") MultipartFile file,
            @PathVariable Long productId) throws IOException;

}
