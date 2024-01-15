package edu.example.demoproject.dtos.image;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDto {
    private Long id;
    private Long productId;
}
