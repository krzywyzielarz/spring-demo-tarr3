package pl.sda.spring.bookstore.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
   @GetMapping("/mvc/hello")
   public String hello(Model model) {
      model.addAttribute("name", "foobar");
      return "hello";
   }
}
