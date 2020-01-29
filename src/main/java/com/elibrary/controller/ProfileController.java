package com.elibrary.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elibrary.model.Librarian;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;
import com.elibrary.service.ILoanService;
import com.elibrary.service.IUserService;

@Controller
public class ProfileController {
	
	@Autowired
	private IUserService userService;
	
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
}