package com.pharmacy.optican.demo.controller;

import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicController {

    private final UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String regUser(){
        return "registration-page";
    }

    @PostMapping("/registration")
    public String addUser(User user){
        userService.saveUser(user);
        return "redirect:/main-page";
    }

    @GetMapping("/main-page")
    public String mainPage(){
        return "main-page";
    }

    @GetMapping("/login-page")
    public String loginPage(){
        return "login-page";
    }

}
