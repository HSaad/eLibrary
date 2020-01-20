package com.elibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.dao.LibraryItemRepository;
import com.elibrary.model.LibraryItem;

@Service
public class LibraryItemService implements ILibraryItemService{
	
	@Autowired
	private LibraryItemRepository repository;
	
	@Override
	public List<LibraryItem> findAll() {
		List<LibraryItem> items = (List<LibraryItem>) repository.findAll();
		return items;
	}

	@Override
	public List<LibraryItem> findByTitle(String title) {
		List<LibraryItem> items = (List<LibraryItem>) repository.findByTitle(title);
		return items;
	}

	@Override
	public List<LibraryItem> findByCreator(String creator) {
		List<LibraryItem> items = (List<LibraryItem>) repository.findByCreator(creator);
		return items;
	}

	@Override
	public List<LibraryItem> findAvailable() {
		List<LibraryItem> items = (List<LibraryItem>) repository.findAvailable();
		return items;
	}

	@Override
	public LibraryItem findByID(Long id) {
		LibraryItem item = (LibraryItem) repository.findOne(id);
		return item;
	}
	
	//Creates new user 
	public LibraryItem create(LibraryItem item) {
		repository.save(item);
		return item;
	}

}
