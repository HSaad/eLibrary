package com.elibrary.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
    
    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
    	session.removeAttribute("loggedInUser");
		session.invalidate();
		return "index";
    }
}