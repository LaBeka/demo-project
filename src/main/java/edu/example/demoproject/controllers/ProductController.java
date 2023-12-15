package edu.example.demoproject.controllers;

import edu.example.demoproject.api.ProductApi;
import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductCriteriaDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {
    final private ProductService productService;

    @Override
    public ProductDto getFullInfoById(Long id) {
        return productService.getFullInfoById(id);
    }

    @Override
    public List<ProductDto> getAll() {
        return productService.getAllProducts();
    }

    @Override
    public List<ProductDto> findByTitle(String title) {
        return productService.find(title);
    }

    @Override
    public List<ProductDto> findByCriteria(ProductCriteriaDto dto) {
        return productService.findByCriteria(dto);
    }

    @Override
    public Long create(ProductCreateDto newProductDto) {
        return productService.create(newProductDto);
    }

    @Override
    public Long update(Long id, ProductCreateDto newProductDto) {
        return productService.updateProductInfo(id, newProductDto);
    }

    @Override
    public void delete(Long id) {
        productService.deleteProduct(id);
    }

}