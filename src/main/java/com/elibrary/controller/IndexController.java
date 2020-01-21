package com.elibrary.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;
import com.elibrary.service.ILibraryItemService;
import com.elibrary.service.ILoanService;
import com.elibrary.service.IUserService;

@Controller
public class IndexController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILibraryItemService itemService;
	
	@Autowired
	private ILoanService loanService;
	
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
    
    @RequestMapping("/index")
    public String index(Model model) {
    	//model.setAttribute("loggedInUser", foundUser);
        return "index";
    }
    
    @RequestMapping("/users")
    public String findAllOrderedByNameDescending(Model model) {
    	List<User> users = (List<User>) userService.findAll();
    	model.addAttribute("users", users);
    	return "user";
    }
    

    @RequestMapping("/signup")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @RequestMapping("/signin")
    public String logInUserForm(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String logInUser(@ModelAttribute User user, Model model) {
    	 model.addAttribute("user", user);
    	return "result";
//		User foundUser = userService.findByEmail(user.getEmail());
//		
//		if(foundUser == null) {
//			System.out.println("User not found!"); //redirect back to login page
//			return "index";
//		}else if(user.getPassword() != null && user.getPassword().equals(foundUser.getPassword())) {
//			List<Loan> loanedItems = loanService.findAllCurrentLoansByUser(foundUser);
//			List<Loan> history = loanService.findByUser(foundUser);
//			
//			model.addAttribute("loggedInUser", foundUser);
//			model.addAttribute("loans", loanedItems);
//			model.addAttribute("history", history);
//
//			//TODO check the type of user and display the appropriate file
//			//go to profile
//		}else {
//			 //redirect back to signin //display error msg for password (wrong password)
//			return "index";
//		}
//		
//        return "signin";
    }
    
    @RequestMapping("/createitemform")
    public String createItemForm(Model model) {
        model.addAttribute("item", new LibraryItem());
        return "createitem";
    }

    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        userService.create(user);
        return "result";
    }
   
    @RequestMapping(value="/createitem", method=RequestMethod.POST)
    public String createItem(@ModelAttribute LibraryItem item, Model model) {
        model.addAttribute("item", item);
        itemService.create(item);
        return "home";
    }

    @RequestMapping("/items")
    public String findAllItems(Model model) {
    	List<LibraryItem> items = (List<LibraryItem>) itemService.findAll();
    	model.addAttribute("items", items);
    	return "item";
    }
    
    @RequestMapping("/loans")
    public String findAllLoans(Model model) {
    	List<Loan> loans = (List<Loan>) loanService.findAll();
    	model.addAttribute("loans", loans);
    	return "loan";
    }
}