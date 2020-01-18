package com.elibrary.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NamedQuery;

import com.elibrary.model.Ebook;
import com.elibrary.model.LibraryItem;

public interface LibraryItem_DAOI extends ICreatable<LibraryItem>, IReadable<LibraryItem>, 
											IUpdatable<LibraryItem>, IDeletable<LibraryItem>{

	List<LibraryItem> readByTitle(String title);
	List<LibraryItem> readByCreator(String creator);
		
	List<LibraryItem> readAllEbooks();
	List<LibraryItem> readAllAudioBooks();
	List<LibraryItem> readAllMagazines();
	List<LibraryItem> readAllVideos();
	List<LibraryItem> readByType(Class type);
	List<LibraryItem> readAllAvailable();
}
