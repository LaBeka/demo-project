package edu.example.demoproject.api;

import edu.example.demoproject.dtos.user.UserCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RequestMapping(UserApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с пользователем", description = UserApi.DICTS_API_PATH)
public interface UserApi {
    String DICTS_API_PATH = "/api/users";

}
