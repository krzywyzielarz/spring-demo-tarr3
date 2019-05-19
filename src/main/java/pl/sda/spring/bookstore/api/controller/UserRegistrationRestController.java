package pl.sda.spring.bookstore.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.spring.bookstore.common.service.UserRegistrationService;
import pl.sda.spring.bookstore.config.User;

@RestController
public class UserRegistrationRestController {

    private UserRegistrationService userRegistrationService;

    public UserRegistrationRestController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/api/users")
    public long register(@RequestBody User user) {
        return userRegistrationService.registerUser(user);
    }
}
