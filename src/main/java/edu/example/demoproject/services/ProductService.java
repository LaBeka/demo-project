package edu.example.demoproject.services;

import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.dtos.product.ProductCriteriaDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.ProductEntity;
import edu.example.demoproject.mappers.ProductMapper;
import edu.example.demoproject.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductDto getFullInfoById(Long id) {
        return productRepository.getFullInfoById(id);
    }
    public List<ProductDto> getAllProducts(){
        List<ProductDto> allProducts = productRepository.getAllProducts();
        return allProducts;
    }
    public List<ProductDto> find(String title){
        return productRepository.findCaseInsensitiveBy(title);
    }

    public List<ProductDto> findByCriteria(ProductCriteriaDto dto) {
        List<ProductEntity> resultList = productRepository.findByCriteria(dto);
        return resultList.stream()
                .map(p -> this.productMapper.productEntityToDto(p))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long create(ProductCreateDto newProductDto) {
        ProductEntity productEntity = productMapper.productDtoToEntity(null, newProductDto);
        productRepository.persist(productEntity);//ProductCreateDto не стала создавать для него айди.
        return productEntity.getId();
    }

    @Transactional
    public Long updateProductInfo(Long id, ProductCreateDto newProductDto) {
        ProductEntity entity = productMapper.productDtoToEntity(id, newProductDto);
        productRepository.merge(entity);
        return entity.getId();
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
