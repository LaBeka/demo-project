package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.brand.BrandDto;
import edu.example.demoproject.dtos.category.CategoryDto;
import edu.example.demoproject.dtos.product.ProductDto;
import edu.example.demoproject.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@AllArgsConstructor
public class ProductMapper {
    private CategoryMapper categoryMapper;
    private BrandMapper brandMapper;
    public ProductDto buildProduct(Product product){
        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        System.out.println(uriBuilder);
        uriBuilder.replacePath("/product/" + product.getId() + "/image");
        product.setImage(uriBuilder.build().toString());

        CategoryDto categoryDto = categoryMapper.buildCategory(product.getCategory());

        BrandDto brandDto = brandMapper.buildBrand(product.getBrand());

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .initialPrice(product.getInitialPrice())
                .discount(product.getDiscount())
                .newProduct(product.isNewProduct())
                .brand(brandDto)
                .category(categoryDto)
                .build();
    }
}
