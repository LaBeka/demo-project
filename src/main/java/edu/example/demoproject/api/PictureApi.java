package edu.example.demoproject.api;

import edu.example.demoproject.dtos.picture.PictureDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping(edu.example.demoproject.api.PictureApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с фотографии продукта", description = edu.example.demoproject.api.PictureApi.DICTS_API_PATH)
public interface PictureApi {

    String DICTS_API_PATH = "/api/image";

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление новой фотографии продукта")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity createPicProduct(
            @Valid @RequestPart("file")MultipartFile file,
            @PathVariable Long id) throws IOException;

    @GetMapping("/showPic/{id}")
    @Operation(summary = "Получение фотографии продукта")
    ResponseEntity showImageByProductId(@PathVariable Long id) throws IOException;

    @GetMapping("/getPic/{id}")
    @Operation(summary = "Получение фотографии продукта")
    List<PictureDto> getImageEntity(@PathVariable Long id) throws IOException;

    @PutMapping("/{id}")
    @Operation(summary = "Изменение фотографии продукта")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity updatePicProductInfo(
            @Valid @RequestPart("file")MultipartFile file,
            @PathVariable Long id) throws IOException;


    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить фотографию продукта по айди фотографии")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long id);

}
