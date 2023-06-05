package edu.example.demoproject.controllers;

import edu.example.demoproject.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {
    final private ItemService itemService;
}
