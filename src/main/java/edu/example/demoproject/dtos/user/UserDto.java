package edu.example.demoproject.dtos.user;

import edu.example.demoproject.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String accountName;
    private String email;
    private String password;
    private boolean enabled;

}
