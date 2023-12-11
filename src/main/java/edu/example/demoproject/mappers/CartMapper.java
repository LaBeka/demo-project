package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.cart.CartDto;
import edu.example.demoproject.entities.CartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartEntity dtoToEntity(CartDto dto);

    CartDto entityToDto(CartEntity entity);
}
