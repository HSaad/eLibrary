package com.elibrary.controller;

import java.time.LocalDate;
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
public class AdminController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILibraryItemService itemService;
	
	@Autowired
	private ILoanService loanService;
    
    @RequestMapping(value="/deleteuser", method=RequestMethod.POST)
    public String deleteUser(HttpServletRequest request, Model model, @RequestParam("user") String id) {
    	Long userId = Long.parseLong(id);
    	userService.deleteById(userId);
    	List<User> librarians = userService.findAllLibrarians();
		request.getSession().setAttribute("librarians", librarians);
    	String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
  
    @RequestMapping(value="/createlibrarian", method=RequestMethod.POST)
    public String createLibrarian(HttpSession session, @ModelAttribute Librarian librarian, Model model) {
        userService.create(librarian);
        List<User> librarians = userService.findAllLibrarians();
		session.setAttribute("librarians", librarians);
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
    
    @RequestMapping(value="/editItem", method=RequestMethod.POST)
    public String editItem(Model model, @ModelAttribute LibraryItem item) {	
		LibraryItem foundItem = itemService.findByID(item.getId());
		model.addAttribute("item", foundItem);
		foundItem.setTitle(item.getTitle());
		foundItem.setDescription(item.getDescription());
		foundItem.setGenre(item.getGenre());
		foundItem.setCreator(item.getCreator());
		foundItem.setPublicationYear(item.getPublicationYear());
		foundItem.setImgSrc(item.getImgSrc());	
		
		itemService.update(foundItem);
		return "index";	
    }
    
    
    @RequestMapping(value="/updateItem", method=RequestMethod.GET)
    public String editDeleteItem(HttpSession session, Model model, @RequestParam("id") String id, @RequestParam("button") String button) {	
    	//User user = (User) session.getAttribute("loggedInUser");
		LibraryItem item = itemService.findByID(Long.parseLong(id));
		
		if("Edit".equals(button)) {
			//go to edit item form
			model.addAttribute("item", item);
			return "editItem";
		}else if ("Delete".equals(button)) {
			if(item.isAvailable()) {
				//delete loans first
				List<Loan> loans = loanService.findByItem(item);
				deleteLoans(loans);
				
				//delete item
				itemService.delete(item);
				return "index";
			}else {
				model.addAttribute("alertMsg", "Item is currently checked out and cannot be deleted at this time");
				return "itemProfile";		
			}

		}
		return "index";
    }
	
	public void deleteLoans(List<Loan> loans) {
		if(loans != null) {
			for (Loan l : loans) {
				loanService.delete(l);
			}
		}	
	}
}