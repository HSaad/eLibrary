package com.elibrary.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elibrary.model.User;
import com.elibrary.service.IUserService;

@Controller
public class SignupController {
	
	@Autowired
	private IUserService userService;
	
    @RequestMapping("/signup")
    public String createUserForm(HttpSession session, Model model) {
    	if(session.getAttribute("loggedInUser") != null) {
    		//change to profile
			return "index";
		}
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {
        userService.create(user);
        return "borrowerProfile";
    }
}