package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity productDtoToEntity(ProductCreateDto productCreateDto);

    ProductDto productEntityToDto(ProductEntity productEntity);
}
