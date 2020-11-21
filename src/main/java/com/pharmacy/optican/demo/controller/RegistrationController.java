package com.pharmacy.optican.demo.controller;
import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration_page")
    public String addUser(User user){
            userService.saveUser(user);
        return "redirect:/user_page";
    }
}
