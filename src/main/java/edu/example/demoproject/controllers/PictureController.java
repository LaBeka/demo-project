package edu.example.demoproject.controllers;

import edu.example.demoproject.api.PictureApi;
import edu.example.demoproject.dtos.picture.PictureCreateDto;
import edu.example.demoproject.services.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;


@RestController
@RequiredArgsConstructor
public class PictureController implements PictureApi {
    private final PictureService pictureService;

    @Override
    public ResponseEntity getImageByProductId(Long id, HttpServletRequest request) throws IOException {
        return pictureService.getPictureByShowId(id, request);
    }

    @Override
    public ResponseEntity createPicProduct(PictureCreateDto pictureCreateDto, HttpServletRequest request) throws MalformedURLException {
        return pictureService.create(pictureCreateDto, request);
    }

    @Override
    public ResponseEntity updatePicProductInfo(PictureCreateDto pictureCreateDto, HttpServletRequest request) throws IOException {
        return pictureService.update(pictureCreateDto, request);
    }

}
