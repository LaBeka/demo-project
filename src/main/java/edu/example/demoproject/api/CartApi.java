package edu.example.demoproject.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CartApi.DICTS_API_PATH)
@Tag(name = "Methods to work with shop cart", description = CartApi.DICTS_API_PATH)
@SecurityRequirement(name = "bearerAuth")
public interface CartApi {
    String DICTS_API_PATH = "/api/carts";

    @GetMapping("/{id}")
    @Operation(summary = "To get the only shop cart by user's id")
    @PreAuthorize("hasAnyRole('USER')")
    ResponseEntity getCartByUserId(@PathVariable Long id);

    @PostMapping("/{id}")
    @Operation(summary = "To create shop cart by user's id")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('USER')")
    Long create(@PathVariable Long id);

    @DeleteMapping("/{id}")
    @Operation(summary = "To delete shop cart by user's id")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('USER')")
    void delete(@PathVariable Long id);

}
