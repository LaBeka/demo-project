package edu.example.demoproject.dtos.auth;
import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}