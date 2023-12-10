package edu.example.demoproject.api;

import edu.example.demoproject.dtos.picture.PictureDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping(edu.example.demoproject.api.PictureApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с фотографии продукта", description = edu.example.demoproject.api.PictureApi.DICTS_API_PATH)
public interface PictureApi {

    String DICTS_API_PATH = "/api/image";

    @GetMapping("/getListPics/{productId}")
    @Operation(summary = "Получение список энтити фотографии по айди продукта")
    List<PictureDto> getListPictureEntity(@PathVariable Long productId) throws IOException;

    @GetMapping("/showPic/{id}")
    @Operation(summary = "Показать фото по айди PictureDto")
    ResponseEntity showImageByItsId(@PathVariable Long id) throws IOException;

    @PostMapping(value = "/add/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление новой фотографии продукта по айди продукта")
    @ResponseStatus(HttpStatus.OK)
    List<PictureDto> createPicOfProduct(
            @Valid @RequestPart("file")MultipartFile file,
            @PathVariable Long productId) throws IOException;

    @PutMapping("/update/{id}")
    @Operation(summary = "Поменять фотографию продукта по айди PictureEntity")
    @ResponseStatus(HttpStatus.OK)
    List<PictureDto> updatePicProductInfo(
            @Valid @RequestPart("file")MultipartFile file,
            @PathVariable Long id) throws IOException;


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить фотографию продукта по айди PictureEntity")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long id);

}
