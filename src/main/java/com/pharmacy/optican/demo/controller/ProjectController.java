package com.pharmacy.optican.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
    @GetMapping("/main_page")
    public String mainPage() {
        return "main-page";
    }

    @GetMapping("/")
    public String rootPage() {
        return "redirect:/main_page";
    }
}
