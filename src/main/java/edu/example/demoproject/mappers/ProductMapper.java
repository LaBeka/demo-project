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
    final private CategoryMapper categoryMapper;
    final private BrandMapper brandMapper;
    public ProductDto buildProduct(Product product){
        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        uriBuilder.replacePath("/product/image/" + product.getId());
        product.setImage(uriBuilder.build().toString());

        CategoryDto categoryDto = categoryMapper.buildCategory(product.getCategory());

        BrandDto brandDto = brandMapper.buildBrand(product.getBrand());

        double price = product.getInitialPrice();
        double discount = product.getDiscount();
        product.setCurrentPrice(price - (discount * price) / 100);
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .initialPrice(product.getInitialPrice())
                .discount(product.getDiscount())
                .currentPrice(String.format("%.2f", product.getCurrentPrice()))
                .newProduct(product.isNewProduct())
                .brand(brandDto)
                .category(categoryDto)
                .build();
    }
}
