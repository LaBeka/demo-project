package edu.example.demoproject.controllers;

import edu.example.demoproject.api.ProductsApi;
import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductsApi {
    final private ProductService productService;

    @Override
    public ProductDto getFullInfoById(Long id) {
        return productService.getFullInfoById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public List<ProductDto> find(String title) {
        return productService.find(title);
    }

    @Override
    public ProductDto createProduct(ProductCreateDto newProductDto) {
        return productService.create(newProductDto);
    }

    @Override
    public void updateProductInfo(Long id, ProductCreateDto newProductDto) {
        productService.updateProductInfo(id, newProductDto);
    }

//    @GetMapping("/allCriteria")
//    @CrossOrigin(origins = "http://localhost:63342")
//    public ResponseEntity getListByCriteria(ProductSearchDto dto, Pageable pageable){
//        PageImpl<ProductEntity> resultList = productService.rawSearch(dto, pageable);
//        return returnResponseEntity(resultList);
//    }
//
//    private ResponseEntity returnResponseEntity(PageImpl<ProductEntity> resultList) {
//        if(resultList.getTotalElements() == 0){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);// error 404 not found any productEntities
//        }
//
//        List<ProductDto> resultListDto =  resultList.stream()
//                .map(p -> this.productMapper.buildProduct(p))
//                .collect(Collectors.toList());
//
//        int totalPages = resultList.getTotalPages();
//        long totalElements = resultList.getTotalElements();
//        int size1 = resultList.getSize();
//        return new ResponseEntity(new PageDto<>(
//                resultListDto,
//                totalPages,
//                totalElements,
//                size1), HttpStatus.OK
//        );
//    }
}