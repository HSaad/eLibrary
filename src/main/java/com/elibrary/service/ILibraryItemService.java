package com.elibrary.service;

import java.util.List;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.User;

public interface ILibraryItemService {
	List<LibraryItem> findAll();
	List<LibraryItem> findByTitle(String title);
	List<LibraryItem> findByCreator(String creator);		
//	List<LibraryItem> findAllAvailable();
	LibraryItem findByID(Long id);
	LibraryItem create(LibraryItem item);	
}