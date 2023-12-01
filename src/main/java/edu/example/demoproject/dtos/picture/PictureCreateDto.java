package edu.example.demoproject.dtos.picture;

import edu.example.demoproject.customValidator.MultipartFileSizeValid;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PictureCreateDto {

    @MultipartFileSizeValid(maxSizeInMB = 10, message = "Size of picture should be  between 5 to 150 mb")
    private MultipartFile name;

    @NotNull( message="Product id is null")
    @Positive
    private Long productId;

}
