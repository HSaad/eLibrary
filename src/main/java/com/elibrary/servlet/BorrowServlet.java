package com.elibrary.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elibrary.dao.LibraryItem_DAOI;
import com.elibrary.dao.Loan_DAOI;
import com.elibrary.daoimpl.LibraryItem_DAO_Impl;
import com.elibrary.daoimpl.Loan_DAO_Impl;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User_JPA;

public class BorrowServlet extends HttpServlet{
	Loan_DAOI ldao = new Loan_DAO_Impl();
	LibraryItem_DAOI lidao = new LibraryItem_DAO_Impl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		LibraryItem item = (LibraryItem)session.getAttribute("item");
		User_JPA user = (User_JPA)session.getAttribute("loggedInUser");
		
		if(user == null) {
			RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
			rd.forward(request, response);
		}else {
		
			item.setAvailable(false);
			lidao.update(item);
			
			Loan loan = new Loan(item, user, LocalDate.now(), null);
			ldao.create(loan);
		
			RequestDispatcher rd = request.getRequestDispatcher("profile"); //forward to borrowerProfile
			rd.forward(request, response);	
		}
	}
	
}
