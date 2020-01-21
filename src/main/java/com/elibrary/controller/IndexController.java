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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elibrary.model.AudioBook;
import com.elibrary.model.Ebook;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.Magazine;
import com.elibrary.model.User;
import com.elibrary.model.Video;
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
    
    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @RequestMapping("/profile")
    public String profile(HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		List<Loan> loanedItems = loanService.findAllCurrentLoansByUser(loggedInUser);
		session.setAttribute("loans", loanedItems);

		//Add user history to session
		List<Loan> history = loanService.findByUser(loggedInUser);
		session.setAttribute("history", history);
		
		ArrayList<User> librarians = (ArrayList<User>) userService.findAllLibrarians();
		session.setAttribute("librarians", librarians);
	
    	return "borrowerProfile";
    }
    
    @RequestMapping("/profileActions")
    public String profileActions(Model model, @RequestParam("button") String button) {
    	model.addAttribute("buttonClicked", button);
    	model.addAttribute("item", new LibraryItem());
    	return "borrowerProfile";
    }
    
    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }
    
    @RequestMapping("/browse")
    public String browse(Model model) {
        return "browse";
    }
    
    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {

		ArrayList<LibraryItem> libraryCatalogue = (ArrayList<LibraryItem>) itemService.findAll();
		session.setAttribute("catalogue", libraryCatalogue);
		
		ArrayList<LibraryItem> ebooks = (ArrayList<LibraryItem>) itemService.findAllEbooks();
		model.addAttribute("ebooks", ebooks);
		
		ArrayList<LibraryItem> audiobooks = (ArrayList<LibraryItem>) itemService.findAllAudioBooks();
		model.addAttribute("audiobooks", audiobooks);
		
        return "index";
    }
    
    @RequestMapping("/users")
    public String findAllOrderedByNameDescending(Model model) {
    	List<User> users = (List<User>) userService.findAll();
    	model.addAttribute("users", users);
    	return "user";
    }
    

    @RequestMapping("/signup")
    public String createUserForm(HttpSession session, Model model) {
    	if(session.getAttribute("loggedInUser") != null) {
    		//change to profile
			return "index";
		}
        model.addAttribute("user", new User());
        return "signup";
    }
    
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
			
			session.setAttribute("loggedInUser", foundUser);
			model.addAttribute("loans", loanedItems);
			model.addAttribute("history", history);
			
			return "borrowerProfile";
			//TODO check the type of user and display the appropriate file
			//go to profile
		}else {
			 //redirect back to signin //display error msg for password (wrong password)
			return "signin";
		}
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
    public String createItem(@ModelAttribute LibraryItem item, Model model, @RequestParam("type") String type) {
    	if(type == "Ebook") {
    		Ebook book = (Ebook) item;
    		itemService.create(book);
    	}else if (type == "Audiobook") {
    		AudioBook audiobook = (AudioBook) item;
    		itemService.create(audiobook);
    	}else if (type == "Magazine"){
    		Magazine magazine = (Magazine) item;
    		itemService.create(magazine);
    	}else {
    		Video video = (Video) item;
    		itemService.create(video);
    	}
        return "browse";
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
    
    @RequestMapping(value="/searched", method=RequestMethod.GET)
    public String searchItems(Model model, @RequestParam("search") String title) {
    	List<LibraryItem> searchResults = itemService.findByTitle(title);
    	model.addAttribute("searchResults", searchResults);
    	return "search-page";
    }
    
    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
    	session.removeAttribute("loggedInUser");
		session.invalidate();
    	return "index";
    }
}