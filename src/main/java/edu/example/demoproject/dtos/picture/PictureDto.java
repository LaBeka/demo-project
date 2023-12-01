package edu.example.demoproject.dtos.picture;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PictureDto {
    private String name;
    private Long productId;
}
