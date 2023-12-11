package edu.example.demoproject.dtos.user;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserCreateDto {
    @NotNull( message="Full name is null, please provide a valid value.")
    @NotBlank( message="Full name is blank, please provide a valid value.")
    @Size(min = 1, max = 16, message="Full name's size is not valid, please provide a valid value.")
    private String fullName;

    @NotNull( message="Account name is null, please provide a valid value.")
    @NotBlank( message="Account name is blank, please provide a valid last name")
    @Size(min = 1, max = 16, message="Account name's size is not valid, please provide a valid lastname")
    private String accountName;

    @NotNull(message="Email is null, please provide a valid email address.")
    @NotBlank(message="Email is blank, please provide a valid email address.")
    @Email(message="Email symbol is not valid, please provide a valid email address")
    private String email;

    @NotNull(message="Please provide a valid password. Password is Null")
    @NotBlank(message="Please provide a valid password. Password isBlank")
    @Size(min = 6, max = 16, message="Please provide a valid password. Password Size has issues")
    private String password;

}
