package edu.example.demoproject.mappers;

import edu.example.demoproject.entities.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "imageName", target = "imageName")
    ImageEntity buildEntity(Long id, Long productId, String imageName);

}
