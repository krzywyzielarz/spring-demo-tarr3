package pl.sda.spring.bookstore.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.spring.bookstore.common.service.UserRegistrationService;
import pl.sda.spring.bookstore.config.User;

@Controller
public class UserRegisterController {

    private UserRegistrationService userRegistrationService;

    public UserRegisterController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("/mvc/register")
    public String showRegistrationForm() {
        return "bookstore/register";
    }

    @PostMapping("/mvc/register")
    public String registerUser(User user){
        userRegistrationService.registerUser(user);
        return "redirect:/mvc/bookstore/all";
    }

}
