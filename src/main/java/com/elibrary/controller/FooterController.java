package com.elibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FooterController {
    
    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }
    
    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }
}