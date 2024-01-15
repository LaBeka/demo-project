package edu.example.demoproject.api;

import edu.example.demoproject.dtos.cart.CartAction;
import edu.example.demoproject.dtos.item.ItemCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping(edu.example.demoproject.api.ItemApi.DICTS_API_PATH)
@Tag(name = "Methods to work with items of shopping cart.", description = edu.example.demoproject.api.ItemApi.DICTS_API_PATH)
@SecurityRequirement(name = "bearerAuth")
public interface ItemApi {

    String DICTS_API_PATH = "/api/items";

    @GetMapping("/{cartId}")
    @Operation(summary = "To get added products from shopping cart")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity getItemsByCartId(@PathVariable Long cartId);//needs to be modified later with auth

    @PostMapping()
    @Operation(summary = "To add new product(as new item) into shopping cart")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN')")
    void addNewItem(@Valid @RequestBody ItemCreateDto dto);

//    @PutMapping("/{userId}/increment/{productId}")
//    @PathVariable Long userId, @PathVariable Long productId,

    @GetMapping("/getActions")
    @Operation(summary = "To get enum of actions(increment and decrement)")
    ResponseEntity getListActions();

    @PutMapping("/action/{productId}")
    @Operation(summary = "Increment or decrement quantity of item(product) in shopping cart by id of product")
    @ResponseStatus(HttpStatus.OK)
    void doAction(@PathVariable Long productId, @RequestBody CartAction action) throws Exception;

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "To delete the product(by id) from shopping cart")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long productId);

}
