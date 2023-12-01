package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.picture.PictureCreateDto;
import edu.example.demoproject.dtos.picture.PictureDto;
import edu.example.demoproject.entities.PictureEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PictureMapper {

    PictureEntity pictureDtoToEntity(PictureCreateDto pictureCreateDto);

    PictureDto productEntityToDto(PictureEntity pictureEntity);
}
