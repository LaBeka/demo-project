package edu.example.demoproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(){
        return "main";
    }

    @GetMapping("/detail")
    public String getProductDetailPage(){
        return "detail";
    }
    @GetMapping("/addProduct")
    public String getAddProdPage(){
        return "addProduct";
    }
}
