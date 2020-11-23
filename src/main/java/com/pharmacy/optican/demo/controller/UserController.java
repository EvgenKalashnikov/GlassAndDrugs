package com.pharmacy.optican.demo.controller;

import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user_page")
    public String userPage(Authentication authentication, Model model) {
        model.addAttribute("user", userService.findUserByEmail(authentication.getName()));
        return "user-page";
    }

    @PostMapping("/update_user")
    public String userUpdate(User user) {
        userService.updateUser(user);
        return "redirect:/user_page";
    }


}
