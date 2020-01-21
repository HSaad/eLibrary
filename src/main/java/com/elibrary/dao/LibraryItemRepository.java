package com.elibrary.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.User;

@Repository
public interface LibraryItemRepository extends CrudRepository<LibraryItem, Long>{
	
	List<LibraryItem> findAll();
	List<LibraryItem> findByTitle(@Param("title") String title);
	List<LibraryItem> findByCreator(@Param("creator") String creator);		
	List<LibraryItem> findByAvailable(@Param("available") boolean available);
	List<LibraryItem> findAllMagazines();
	List<LibraryItem> findAllAudioBooks();
	List<LibraryItem> findAllVideos();
	List<LibraryItem> findAllEbooks();
	List<LibraryItem> findByType(Class type);
}
