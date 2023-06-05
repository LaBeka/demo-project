package edu.example.demoproject.controllers;


import edu.example.demoproject.dtos.brand.BrandDto;
import edu.example.demoproject.dtos.product.ProductSearchDto;
import edu.example.demoproject.entities.Brand;
import edu.example.demoproject.entities.Product;
import edu.example.demoproject.mappers.BrandMapper;
import edu.example.demoproject.services.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/brand")
@AllArgsConstructor
public class BrandController {
    final private BrandService brandService;
    final private BrandMapper brandMapper;

    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity getListOfBrands(){
        List<Brand> brands = brandService.get();
        if(brands.size() == 0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<BrandDto> collect = brands.stream()
                .map(b -> this.brandMapper.buildBrand(b))
                .collect(Collectors.toList());
        return new ResponseEntity(collect, HttpStatus.OK);
    }
}
