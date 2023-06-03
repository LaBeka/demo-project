package edu.example.demoproject.mappers;

import edu.example.demoproject.dtos.user.UserDto;
import edu.example.demoproject.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto buildUser(User user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .accountName(user.getAccountName())
                .email(user.getEmail())
                .build();
    }
}
