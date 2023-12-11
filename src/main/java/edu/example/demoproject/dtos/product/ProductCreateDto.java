package edu.example.demoproject.dtos.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ProductCreateDto {
    @NotNull( message="Product name is null")
    @NotBlank( message="ProductEntity name is blank")
    @Size(min = 1, max = 16, message="Please provide a valid productEntity name length")
    private String name;//

    @NotNull( message="ProductEntity description is null")
    @NotBlank( message="ProductEntity description is blank")
    @Size(min = 1, max = 100, message="Please provide a valid productEntity description length")
    @JsonProperty(value = "description")
    private String description;//

    @NotNull( message="Price is null")
    @Digits(integer = 6, fraction = 2, message = "Price digits must be a valid number with a maximum of 3 integral digits and 2 fractional digits")
    @Positive(message = "Price has negative value or zero")
    private Double initialPrice;//

    @NotNull( message="Quantity is null")
    @Digits(integer = 9, fraction = 0, message = "Quantity must be a valid number with a maximum of 3 integral digits and 2 fractional digits")
    @Positive(message = "Quantity has negative value or zero")
    private Integer storageQty;//

    @NotNull( message="Discount is null")
    @Digits(integer = 3, fraction = 0, message = "Discount must be a valid number with a maximum of 3 integral digits and 2 fractional digits")
    @Positive(message = "Discount has negative value or zero")
    private Integer discount;//

    @NotNull( message="Brand is null")
    @JsonProperty(value = "brand")
    private String brand;

    @NotNull( message="Category is null")
    @JsonProperty(value = "category")
    private String category;

}
