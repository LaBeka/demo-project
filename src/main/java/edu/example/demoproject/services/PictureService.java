package edu.example.demoproject.services;

import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.ImageEntity;
import edu.example.demoproject.entities.PictureEntity;
import edu.example.demoproject.exception.ImageUploadException;
import edu.example.demoproject.mappers.ImageMapper;
import edu.example.demoproject.mappers.PictureMapper;
import edu.example.demoproject.properties.MinioProperties;
import edu.example.demoproject.repos.ImageRepository;
import edu.example.demoproject.repos.PictureRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;
    private final ImageRepository imageRepository;
    private final PictureMapper pictureMapper;
    private final ImageMapper imageMapper;
    private final MinioProperties minioProperties;
    private final MinioClient minioClient;

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
    public void uploadImage(MultipartFile image, Long productId){
        String imageName = upload(image);
        ImageEntity entity = imageMapper.buildEntity(null, productId, imageName);
        imageRepository.persist(entity);
    }

    public String upload(MultipartFile image){
        try {
            createBucket();
        } catch (Exception e) {
            throw new ImageUploadException("Image upload failed: " + e.getMessage());
        }
        if(image.isEmpty() || image.getOriginalFilename() == null){
            throw new ImageUploadException("Image file empty");
        }
        String filename = generateFileName(image);
        InputStream inputStream;
        try {
            inputStream = image.getInputStream();
        } catch (IOException e) {
            throw new ImageUploadException("Image upload failed: " + e.getMessage());
        }
        saveImage(inputStream, filename);
        return filename;
    }

    @SneakyThrows
    private void createBucket(){
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucket())
                .build());
        if(!found){
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .build());
        }
    }

    private String generateFileName(MultipartFile image) {
        String extension = getExtension(image);
        return UUID.randomUUID() + " " + extension;
    }

    private String getExtension(MultipartFile image) {
        return image.getOriginalFilename()
                .substring(image.getOriginalFilename().lastIndexOf(".") + 1);
    }

    @SneakyThrows
    private void saveImage(InputStream inputStream, String filename) {
        minioClient.putObject(PutObjectArgs.builder()
                        .stream(inputStream, inputStream.available(), -1)
                        .bucket(minioProperties.getBucket())
                        .object(filename)
                .build());
    }

}
