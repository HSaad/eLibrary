package com.elibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elibrary.model.LibraryItem;
import com.elibrary.service.ILibraryItemService;

@Controller
public class SearchController {

	@Autowired
	private ILibraryItemService itemService;
 
    @RequestMapping(value="/searched", method=RequestMethod.GET)
    public String searchItems(Model model, @RequestParam("search") String title) {
    	List<LibraryItem> searchResults = itemService.findByTitle(title);
    	model.addAttribute("searchResults", searchResults);
    	return "search-page";
    }
}