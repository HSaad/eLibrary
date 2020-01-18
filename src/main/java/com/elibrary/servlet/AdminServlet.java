package com.elibrary.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elibrary.dao.LibraryItem_DAOI;
import com.elibrary.dao.Loan_DAOI;
import com.elibrary.dao.User_JPA_DAOI;
import com.elibrary.daoimpl.LibraryItem_DAO_Impl;
import com.elibrary.daoimpl.Loan_DAO_Impl;
import com.elibrary.daoimpl.User_JPA_DAO_Impl;
import com.elibrary.model.Librarian;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User_JPA;


public class AdminServlet extends HttpServlet{
	Loan_DAOI ldao = new Loan_DAO_Impl();
	LibraryItem_DAOI lidao = new LibraryItem_DAO_Impl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		
		String password = request.getParameter("pw");
		
		System.out.println("INSIDE THE ADMIN SERVLET ");
		
		User_JPA_DAOI udao = new User_JPA_DAO_Impl();
		//need to decide here whether user is a borrower, admin, or librarian
		//checking by username + email (unique) to see if the user already exists in the database
		//checking will be done by a validation servlet
		//By this point the data will be for a new user and the inputs will be validated
		//TODO check that pw == pw1
	
		Librarian user = (Librarian) new Librarian().setEmail(email).setFirstName(firstName).setLastName(lastName).setPassword(password).setUsername(username);
		
		udao.create(user);

		RequestDispatcher rd = request.getRequestDispatcher("profile"); //forward to borrowerProfile
		rd.forward(request, response);	

	}
	
	
}
