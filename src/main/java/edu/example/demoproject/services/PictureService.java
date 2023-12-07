package edu.example.demoproject.services;

import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.PictureEntity;
import edu.example.demoproject.repos.PictureRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;

    @Transactional
    public ResponseEntity create(MultipartFile file, Long id) throws IOException {
        PictureEntity entity = getPictureEntity(file, id);
        pictureRepository.persist(entity);
        return showBackPicture(entity);
    }

    public ResponseEntity getPictureByShowId(Long id)  {
        Optional<PictureEntity> entity = pictureRepository.findPictureEntityByProductId(id);
        if(entity.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return showBackPicture(entity.get());
    }

    @Transactional
    public ResponseEntity update(MultipartFile newFile, Long productId) throws IOException {
        Optional<PictureEntity> entity = pictureRepository.findPictureEntityByProductId(productId);
        PictureEntity newEntity = getPictureEntity(newFile, productId);
        pictureRepository.update(entity.get().getId(), newEntity);
        return showBackPicture(newEntity);
    }

    public List<PictureDto> getImagesOfProduct(Long productId) {
        return pictureRepository.getImagesOfProduct(productId);
    }

    @Transactional
    public void delete(Long pictureId) {
        pictureRepository.delete(pictureId);
    }

    private static ResponseEntity<InputStreamResource> showBackPicture(PictureEntity entity) {
        return ResponseEntity.ok()
                .contentLength(entity.getImage().length)
                .contentType(MediaType.parseMediaType(entity.getImageContentType()))
                .body(new InputStreamResource(new ByteArrayInputStream(entity.getImage())));
    }

    private static PictureEntity getPictureEntity(MultipartFile file, Long id) throws IOException {
        String contentType = file.getContentType();
        byte[] picture = file.getBytes();
        PictureEntity entity = PictureEntity.builder()
                .id(null)
                .productId(id)
                .imageContentType(contentType)
                .image(picture)
                .build();
        return entity;
    }
}
