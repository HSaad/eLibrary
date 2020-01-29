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
public class LoanController {
	
	@Autowired
	private ILibraryItemService itemService;
	
	@Autowired
	private ILoanService loanService;

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