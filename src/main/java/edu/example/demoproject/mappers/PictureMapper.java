package edu.example.demoproject.mappers;

import edu.example.demoproject.entities.PictureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PictureMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "contentType", target = "imageContentType")
    @Mapping(source = "picture", target = "image")
    PictureEntity buildEntity(Long id, Long productId, String contentType, byte[] picture);
}
