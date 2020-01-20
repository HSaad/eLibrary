package com.elibrary.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.User;

@Repository
public interface LibraryItemRepository extends CrudRepository<LibraryItem, Long>{
	
	List<LibraryItem> findAll();
	List<LibraryItem> findByTitle(String title);
	List<LibraryItem> findByCreator(String creator);		
	List<LibraryItem> findByAvailable(boolean available);
	List<LibraryItem> findAllMagazines();
	List<LibraryItem> findAllAudioBooks();
}
