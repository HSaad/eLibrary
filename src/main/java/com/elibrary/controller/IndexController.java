package com.elibrary.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elibrary.model.LibraryItem;
import com.elibrary.service.ILibraryItemService;

@Controller
public class IndexController {

	@Autowired
	private ILibraryItemService itemService;

    
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
}