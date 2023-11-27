package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.brand.BrandDto;
import edu.example.demoproject.dtos.category.CategoryDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@AllArgsConstructor
public class ProductMapper {
    final private CategoryMapper categoryMapper;
    final private BrandMapper brandMapper;
    public ProductDto buildProduct(ProductEntity productEntity){
        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        uriBuilder.replacePath("/productEntity/image/" + productEntity.getId());
        productEntity.setImage(uriBuilder.build().toString());

        CategoryDto categoryDto = categoryMapper.buildCategory(productEntity.getCategory());
        BrandDto brandDto = brandMapper.buildBrand(productEntity.getBrand());
        String newProduct = productEntity.isNewProduct() ? "new" : " ";
        double price = productEntity.getInitialPrice();
        double discount = productEntity.getDiscount();
        productEntity.setCurrentPrice(price - (discount * price) / 100);
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .image(productEntity.getImage())
                .description(productEntity.getDescription())
                .initialPrice(productEntity.getInitialPrice())
                .discount(productEntity.getDiscount())
                .currentPrice(String.format("%.2f", productEntity.getCurrentPrice()))
                .newProduct(newProduct)
                .brand(brandDto)
                .category(categoryDto)
                .build();
    }
}
