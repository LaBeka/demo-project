package edu.example.demoproject.services;

import edu.example.demoproject.dtos.picture.PictureCreateDto;
import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.PictureEntity;
import edu.example.demoproject.mappers.PictureMapper;
import edu.example.demoproject.repos.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;
    private final PictureMapper pictureMapper;

    public ResponseEntity getPictureByShowId(Long id, HttpServletRequest request) throws IOException {
        PictureDto foundPicture = pictureRepository.findPictureEntityByProductId(id);
        return setPathImage(foundPicture, request);
    }

    @Transactional
    public ResponseEntity create(PictureCreateDto dto, HttpServletRequest request) throws MalformedURLException {
        PictureEntity pictureEntity = pictureMapper.pictureDtoToEntity(dto);
        pictureRepository.persist(pictureEntity);
        PictureDto pictureDto = pictureMapper.productEntityToDto(pictureEntity);
        return setPathImage(pictureDto, request);
    }

    @Transactional
    public ResponseEntity update(PictureCreateDto pictureCreateDto, HttpServletRequest request) throws IOException {
        pictureRepository.update(pictureCreateDto);
        return getPictureByShowId(pictureCreateDto.getProductId(), request);
    }



    private ResponseEntity setPathImage(PictureDto dto, HttpServletRequest request) throws MalformedURLException {
        Path ImageDirectory = Paths.get("storage", dto.getName());
        Resource resource = new UrlResource(ImageDirectory.toUri());

        String contentType = null;
        try {
            contentType = request.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }
        if (contentType == null) {// Fallback to the default content type if type could not be determined
            contentType = "application/octet-stream";//binary data == octet-stream
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
