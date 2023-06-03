package edu.example.demoproject.dtos.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.example.demoproject.customValidator.MultipartFileSizeValid;
import edu.example.demoproject.entities.Brand;
import edu.example.demoproject.entities.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
public class ProductCreateDto {
    @NotNull( message="Product name is null")
    @NotBlank( message="Product name is blank")
    @Size(min = 1, max = 16, message="Please provide a valid product name length")
    private String name;

    @MultipartFileSizeValid(maxSizeInMB = 10, message = "Size of picture should be  between 5 to 150 mb")
    private MultipartFile image;

    @NotNull( message="Product description is null")
    @NotBlank( message="Product description is blank")
    @Size(min = 1, max = 100, message="Please provide a valid product description length")
    @JsonProperty(value = "description")
    private String description;

    @NotNull( message="Price is null")
    @Digits(integer = 3, fraction = 2, message = "Price digits must be a valid number with a maximum of 3 integral digits and 2 fractional digits")
    @Positive(message = "Price has negative value or zero")
    private Double initialPrice;

    @NotNull( message="Quantity is null")
    @Digits(integer = 3, fraction = 0, message = "Quantity must be a valid number with a maximum of 3 integral digits and 2 fractional digits")
    @Positive(message = "Quantity has negative value or zero")
    private Integer storageQty;

    @NotEmpty( message="Discount is empty")
    @Digits(integer = 3, fraction = 0, message = "Discount must be a valid number with a maximum of 3 integral digits and 2 fractional digits")
    @Positive(message = "Discount has negative value or zero")
    private Integer discount;

    @NotNull( message="Brand is null")
    @JsonProperty(value = "brand")
    private Brand brand;

    @NotNull( message="Category is null")
    @JsonProperty(value = "category")
    private Category category;

}
