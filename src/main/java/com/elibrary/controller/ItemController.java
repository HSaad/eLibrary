package com.elibrary.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elibrary.model.LibraryItem;
import com.elibrary.service.ILibraryItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ILibraryItemService itemService;
    
    @RequestMapping(value="/itemProfile", method=RequestMethod.GET)
    public String itemProfile(HttpServletRequest request, Model model, @RequestParam("itemID") String id) {
    	Long itemId = Long.parseLong(id);
    	LibraryItem item = itemService.findByID(itemId);
    	model.addAttribute("item", item);
        return "itemProfile";
    }
}