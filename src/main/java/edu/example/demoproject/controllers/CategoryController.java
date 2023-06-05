package edu.example.demoproject.controllers;

import edu.example.demoproject.dtos.category.CategoryDto;
import edu.example.demoproject.entities.Category;
import edu.example.demoproject.mappers.CategoryMapper;
import edu.example.demoproject.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@Validated
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    final private CategoryService categoryService;
    final private CategoryMapper categoryMapper;

    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity getListOfBrands(){
        List<Category> categories = categoryService.get();
        if(categories.size() == 0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<CategoryDto> collect = categories.stream()
                .map(c -> this.categoryMapper.buildCategory(c))
                .collect(Collectors.toList());
        return new ResponseEntity(collect, HttpStatus.OK);
    }
}
