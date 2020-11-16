package com.pharmacy.optican.demo.controller;
import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user-page")
    public String userPage(Authentication authentication, Model model){
        model.addAttribute("user",userService.findUserByName(authentication.getName()).get());
        return "user-page";
    }

    @PostMapping("/update-user")
    public String userUpdate(User user){
        Optional<User> oldUser = userService.findUserByName(user.getName());
        oldUser.ifPresent(u ->{
            u.setPassword(user.getPassword());
            u.setEmail(user.getEmail());
            u.setPhone(user.getPhone());
            u.setFirstname(user.getFirstname());
            u.setLastname(user.getLastname());
            userService.saveUser(u);
        });
        return "redirect:/user-page";
    }




}
