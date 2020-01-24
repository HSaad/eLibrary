package com.elibrary.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import com.elibrary.model.Librarian;
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
		
		List<User> librarians = userService.findAllLibrarians();
		session.setAttribute("librarians", librarians);
	
    	return "borrowerProfile";
    }
    
    @RequestMapping("/profileActions")
    public String profileActions(Model model, @RequestParam("button") String button) {
    	model.addAttribute("buttonClicked", button);
    	model.addAttribute("item", new LibraryItem());
    	model.addAttribute("user", new User());
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
    	List<User> users = (List<User>) userService.findAllLibrarians();
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
    
    @RequestMapping(value="/deleteuser", method=RequestMethod.POST)
    public String deleteUser(Model model, @RequestParam("user") String id) {
    	Long userId = Long.parseLong(id);
    	userService.deleteById(userId);
    	return "index";
    	
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
    
    @RequestMapping("/createitemform")
    public String createItemForm(Model model) {
        model.addAttribute("item", new LibraryItem());
        return "createitem";
    }

    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {
        userService.create(user);
        return "borrowerProfile";
    }
   
    @RequestMapping(value="/createitem", method=RequestMethod.POST)
    public String createItem(@ModelAttribute LibraryItem item, Model model, @RequestParam("classType") String type) {
		String title = item.getTitle();
		String description = item.getDescription();
		String genre = item.getGenre();
		String creator = item.getCreator();
		int publicationYear = item.getPublicationYear();
		String imgSrc = item.getImgSrc();
    	
    	if(type.equals("Ebook")) {
    		Ebook ebook =  ((Ebook) new Ebook().setTitle(title).setDescription(description).setDateAdded(LocalDate.now()).setAvailable(true).
					setGenre(genre).setCreator(creator).setPublicationYear(publicationYear).setImgSrc(imgSrc));
    		itemService.create(ebook);
    	}else if (type.equals("Audiobook")) {
    		AudioBook audiobook =  ((AudioBook) new AudioBook().setTitle(title).setDescription(description).setDateAdded(LocalDate.now()).setAvailable(true).
					setGenre(genre).setCreator(creator).setPublicationYear(publicationYear).setImgSrc(imgSrc));
    		itemService.create(audiobook);
    	}else if (type.equals("Magazine")){
    		Magazine magazine =  ((Magazine) new Magazine().setTitle(title).setDescription(description).setDateAdded(LocalDate.now()).setAvailable(true).
					setGenre(genre).setCreator(creator).setPublicationYear(publicationYear).setImgSrc(imgSrc));
    		itemService.create(magazine);
    	}else if (type.equals("Video")){
    		Video video =  ((Video) new Video().setTitle(title).setDescription(description).setDateAdded(LocalDate.now()).setAvailable(true).
					setGenre(genre).setCreator(creator).setPublicationYear(publicationYear).setImgSrc(imgSrc));
    		itemService.create(video);
    	} else {
    		itemService.create(item);
    	}
    	
        return "profile";
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