package com.elibrary.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    
    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }
    
    @RequestMapping("/browse")
    public String browse(Model model) {
        return "browse";
    }
    
    @RequestMapping("/")
    public String index(HttpSession session, Model model) {

		ArrayList<LibraryItem> libraryCatalogue = (ArrayList<LibraryItem>) itemService.findAll();
		session.setAttribute("catalogue", libraryCatalogue);
		
		ArrayList<LibraryItem> ebooks = (ArrayList<LibraryItem>) itemService.findAllEbooks();
		model.addAttribute("ebooks", ebooks);
		
		ArrayList<LibraryItem> audiobooks = (ArrayList<LibraryItem>) itemService.findAllAudioBooks();
		model.addAttribute("audiobooks", audiobooks);
		
        return "index";
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
    
    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {
        userService.create(user);
        return "borrowerProfile";
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
    
    @RequestMapping(value="/itemProfile", method=RequestMethod.GET)
    public String itemProfile(HttpServletRequest request, Model model, @RequestParam("itemID") String id) {
    	Long itemId = Long.parseLong(id);
    	LibraryItem item = itemService.findByID(itemId);
    	model.addAttribute("item", item);
        return "itemProfile";
    }
}