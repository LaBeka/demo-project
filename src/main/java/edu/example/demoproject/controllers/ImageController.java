package edu.example.demoproject.controllers;

import edu.example.demoproject.api.ImageApi;
import edu.example.demoproject.services.ImageService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Controller
@RequiredArgsConstructor
public class ImageController implements ImageApi {
    private final ImageService service;

    @Override
    public void createImage(MultipartFile file, Long productId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        service.uploadImage(file, productId);
    }

    @Override
    public ResponseEntity showImageByItsId(Long id) throws IOException {
        return service.getPictureByItsId(id);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public void updatePicProductInfo(MultipartFile file, Long id) throws IOException {
        service.update(file, id);
    }

    @Override
    public ResponseEntity getListImages(Long productId) {
        return ResponseEntity.ok().body(service.getListPictureDtoOfProduct(productId));
    }
}
