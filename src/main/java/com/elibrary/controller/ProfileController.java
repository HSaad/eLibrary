package com.elibrary.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elibrary.model.Librarian;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;
import com.elibrary.service.ILibraryItemService;
import com.elibrary.service.ILoanService;
import com.elibrary.service.IUserService;

@Controller
public class ProfileController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILibraryItemService itemService;
	
	@Autowired
	private ILoanService loanService;

    @RequestMapping("/profile")
    public String profile(HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		List<Loan> loanedItems = loanService.findAllCurrentLoansByUser(loggedInUser);
		session.setAttribute("loans", loanedItems);

		//Add user history to session
		List<Loan> history = loanService.findByUser(loggedInUser);
		session.setAttribute("history", history);
		
		List<User> librarians = userService.findAllLibrarians();
		session.setAttribute("librarians", librarians);
	
    	return "borrowerProfile";
    }
    
    @RequestMapping("/profileActions")
    public String profileActions(Model model, @RequestParam("button") String button) {
    	model.addAttribute("buttonClicked", button);
    	model.addAttribute("item", new LibraryItem());
    	model.addAttribute("librarian", new Librarian());
    	return "borrowerProfile";
    }
   
    @RequestMapping(value="/borrow", method=RequestMethod.GET)
    public String borrow(HttpSession session, Model model, @RequestParam("id") String id) {	
    	User user = (User) session.getAttribute("loggedInUser");
    	if (user == null) {
    		model.addAttribute("user", new User());
    		return "signin";
    	}
		LibraryItem item = itemService.findByID(Long.parseLong(id));
		item.setAvailable(false);
		Loan loan = new Loan(item, user, LocalDate.now(), null);
		loanService.create(loan);
		
		return "borrowerProfile";	
    }
    
    @RequestMapping(value="/loan", method=RequestMethod.GET)
    public String loan(HttpSession session, Model model, @RequestParam("itemID") String id, @RequestParam("button") String button) {	
    	
		LibraryItem item = itemService.findByID(Long.parseLong(id));
		User user = (User) session.getAttribute("loggedInUser");
		Loan loan = loanService.findByUserAndItem(user, item);
		
				
		if ("Renew".equals(button)) { 
			loan.setBorrowedDate(LocalDate.now());
			loanService.update(loan);
			
			return "borrowerProfile";
		}else if ("Return".equals(button)) {
			//update item and set item to available
			item.setAvailable(true);
			itemService.update(item);
			
			//set the return date to current date and update loan
			loan.setReturnedDate(LocalDate.now());
			loanService.update(loan);
			
			return "borrowerProfile";	
		}
		
		return "index";	
    }
}