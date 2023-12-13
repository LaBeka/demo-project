package edu.example.demoproject.controllers;

import edu.example.demoproject.api.ImageApi;
import edu.example.demoproject.services.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
public class ImageController implements ImageApi {
    private final PictureService service;

    @Override
    public void createImage(MultipartFile file, Long productId) {
        service.uploadImage(file, productId);
    }
}
