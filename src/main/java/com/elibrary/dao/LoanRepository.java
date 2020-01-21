package com.elibrary.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elibrary.model.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long>{
	
	List<Loan> findAll();
//	List<LibraryItem> findByTitle(String title);
//	List<LibraryItem> findByCreator(String creator);		
//	List<LibraryItem> findByAvailable(boolean available);
//	List<LibraryItem> findAllMagazines();
//	List<LibraryItem> findAllAudioBooks();
//	List<LibraryItem> findAllVideos();
//	List<LibraryItem> findAllEbooks();
//	List<LibraryItem> findByType(Class type);
}
