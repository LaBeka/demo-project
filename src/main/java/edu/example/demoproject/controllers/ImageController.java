package edu.example.demoproject.controllers;

import edu.example.demoproject.api.ImageApi;
import edu.example.demoproject.services.PictureService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Controller
@RequiredArgsConstructor
public class ImageController implements ImageApi {
    private final PictureService service;

    @Override
    public void createImage(MultipartFile file, Long productId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        service.uploadImage(file, productId);
    }
}
