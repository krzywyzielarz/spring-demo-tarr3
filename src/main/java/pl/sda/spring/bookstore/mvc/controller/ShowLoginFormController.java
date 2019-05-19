package pl.sda.spring.bookstore.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowLoginFormController {
    @GetMapping("/mvc/login")
    public String showLogin() {
        return "bookstore/login";
    }
}
