package edu.example.demoproject.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(RoleApi.DICTS_API_PATH)
@Tag(name = "Методы для работы с ролями", description = RoleApi.DICTS_API_PATH)
public interface RoleApi {
    String DICTS_API_PATH = "/api/roles";

    @GetMapping("/getRoles")
    @Operation(summary = "Получить перечисление ролей")
    ResponseEntity getListRoles();
}
