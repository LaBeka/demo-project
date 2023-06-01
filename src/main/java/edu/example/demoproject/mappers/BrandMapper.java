package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.brand.BrandDto;
import edu.example.demoproject.entities.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    public BrandDto buildBrand(Brand brand){
        return BrandDto.builder()
                .name(brand.getName())
                .build();
    }
}
