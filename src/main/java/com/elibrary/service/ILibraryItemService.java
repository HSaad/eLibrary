package com.elibrary.service;

import java.util.List;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.User;

public interface ILibraryItemService {
	List<LibraryItem> findAll();
	List<LibraryItem> findByTitle(String title);
	List<LibraryItem> findByCreator(String creator);		
	List<LibraryItem> findByAvailable(boolean available);
	List<LibraryItem> findAllAvailable();
	LibraryItem findByID(Long id);
	LibraryItem create(LibraryItem item);	
	List<LibraryItem> findAllMagazines();
	List<LibraryItem> findAllAudioBooks();
	List<LibraryItem> findAllVideos();
	List<LibraryItem> findAllEbooks();
	List<LibraryItem> findByType(Class type);
	void deleteById(Long id);
	void delete(LibraryItem item);
	void update(LibraryItem item);
}
