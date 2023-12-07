package edu.example.demoproject.controllers;

import edu.example.demoproject.api.PictureApi;
import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.services.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class PictureController implements PictureApi {
    private final PictureService pictureService;

    @Override
    public ResponseEntity createPicProduct(MultipartFile file, Long id) throws IOException {
        return pictureService.create(file, id);
    }

    @Override
    public ResponseEntity showImageByProductId(Long id) throws IOException {
        return pictureService.getPictureByShowId(id);
    }

    @Override
    public List<PictureDto> getImageEntity(Long id)  {
        return pictureService.getImagesOfProduct(id);
    }

    @Override
    public ResponseEntity updatePicProductInfo(MultipartFile file, Long id) throws IOException {
        return pictureService.update(file, id);
    }

    @Override
    public void delete(Long id) {
        pictureService.delete(id);
    }

}
