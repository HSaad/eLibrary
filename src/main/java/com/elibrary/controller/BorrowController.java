package com.elibrary.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;
import com.elibrary.service.ILibraryItemService;
import com.elibrary.service.ILoanService;

@Controller
public class BorrowController {
	
	@Autowired
	private ILibraryItemService itemService;
	
	@Autowired
	private ILoanService loanService;
   
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
}