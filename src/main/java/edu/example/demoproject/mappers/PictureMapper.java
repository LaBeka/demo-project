package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.picture.PictureCreateDto;
import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.PictureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PictureMapper {

    @Mapping(expression = "java(pictureCreateDto.getName().getName())", target = "name")
    PictureEntity pictureDtoToEntity(PictureCreateDto pictureCreateDto);

    PictureDto productEntityToDto(PictureEntity pictureEntity);
}
