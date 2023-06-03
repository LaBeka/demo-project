package edu.example.demoproject.dtos.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String accountName;
    private String email;

}
