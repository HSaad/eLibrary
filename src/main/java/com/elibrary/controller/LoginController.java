package com.elibrary.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elibrary.model.Loan;
import com.elibrary.model.User;
import com.elibrary.service.ILoanService;
import com.elibrary.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoanService loanService;
    
    @RequestMapping("/signin")
    public String logInUserForm(HttpSession session, Model model) {
    	if(session.getAttribute("loggedInUser") != null) {
    		//change to profile
			return "index";
		}
        model.addAttribute("user", new User());
        return "signin";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String logInUser(HttpSession session, @ModelAttribute User user, Model model) {
    	String email = user.getEmail();
    	String password = user.getPassword();
		User foundUser = userService.findByEmail(email);
		
		if(foundUser == null) {
			System.out.println("User not found!"); 
			//redirect back to login page
			return "signin";
		}else if(password != null && password.equals(foundUser.getPassword())) {
			List<Loan> loanedItems = loanService.findAllCurrentLoansByUser(foundUser);
			List<Loan> history = loanService.findByUser(foundUser);
			List<User> librarians = userService.findAllLibrarians();
			
			session.setAttribute("librarians", librarians);
			session.setAttribute("loggedInUser", foundUser);
			session.setAttribute("loans", loanedItems);
			session.setAttribute("history", history);
			
			return "borrowerProfile";
		}else {
			 //redirect back to signin //display error msg for password (wrong password)
			return "signin";
		}
    }
}