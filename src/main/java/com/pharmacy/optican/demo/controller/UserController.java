package com.pharmacy.optican.demo.controller;
import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main-page")
    public String mainPage(){
        return "main-page";
    }


    @GetMapping("/registration")
    public String regUser(){
        return "registration-page";
    }
    @PostMapping("/registration")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/main-page";
    }

}
