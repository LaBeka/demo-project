package edu.example.demoproject.services;

import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.PictureEntity;
import edu.example.demoproject.mappers.PictureMapper;
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
    private final PictureMapper pictureMapper;

    public List<PictureDto> getListPictureDtoOfProduct(Long productId) {
        return pictureRepository.getImagesOfProduct(productId);
    }

    public ResponseEntity getPictureByItsId(Long id)  {
        PictureEntity entity = pictureRepository.getPictureByItsId(id);
        return showBackPicture(entity);
    }

    @Transactional
    public List<PictureDto> createPicOfProduct(MultipartFile file, Long productId) throws IOException {
        PictureEntity entity = pictureMapper.buildEntity(null, productId, file.getContentType(), file.getBytes());
        pictureRepository.persist(entity);
        return getListPictureDtoOfProduct(productId);
    }

    @Transactional
    public List<PictureDto> update(MultipartFile newFile, Long id) throws IOException {
        Long productId = pictureRepository.getPictureByItsId(id).getProductId();//getonlyid
        PictureEntity newEntity = pictureMapper.buildEntity(id, productId, newFile.getContentType(), newFile.getBytes());
        pictureRepository.merge(newEntity);
        return getListPictureDtoOfProduct(newEntity.getProductId());
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
}
