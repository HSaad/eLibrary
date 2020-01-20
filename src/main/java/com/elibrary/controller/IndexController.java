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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.User;
import com.elibrary.service.ILibraryItemService;
import com.elibrary.service.IUserService;

@Controller
public class IndexController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILibraryItemService itemService;
	
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
    
    @RequestMapping("/users")
    public String findAllOrderedByNameDescending(Model model) {
    	List<User> users = (List<User>) userService.findAll();
    	model.addAttribute("users", users);
    	return "user";
    }
    

    @RequestMapping("/createuserform")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
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
        return "item";
    }

    @RequestMapping("/items")
    public String findAllItems(Model model) {
    	List<LibraryItem> items = (List<LibraryItem>) itemService.findAll();
    	model.addAttribute("items", items);
    	return "item";
    }
}