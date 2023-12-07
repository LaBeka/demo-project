package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.ProductEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(constant = "true", target = "newProduct")
    @Mapping(target = "currentPrice", expression = "java(dto.getInitialPrice() - (dto.getInitialPrice() * dto.getDiscount() / 100))")
    ProductEntity productDtoToEntity(ProductCreateDto dto);

    @Mapping(constant = "true", target = "newProduct")
    ProductDto productEntityToDto(ProductEntity productEntity);

}
