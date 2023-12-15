package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.item.ItemCreateDto;
import edu.example.demoproject.dtos.item.ItemDto;
import edu.example.demoproject.entities.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto entityToDto(ItemEntity entity);

    @Mapping(constant = "1", target = "qty")
    ItemEntity dtoToEntity(ItemCreateDto dto);
}
