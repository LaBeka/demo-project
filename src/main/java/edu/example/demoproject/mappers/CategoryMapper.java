package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.category.CategoryDto;
import edu.example.demoproject.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto buildCategory(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
