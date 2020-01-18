package com.elibrary.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.elibrary.dao.LibraryItem_DAOI;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.User_JPA;

public class LibraryItem_DAO_Impl implements LibraryItem_DAOI{
	private DbConnection connection;
	
	public LibraryItem_DAO_Impl() {
		connection = DbConnection.getInstance();
	}
	
	//Creates a new libraryItem
	@Override
	public LibraryItem create(LibraryItem item) {
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin(); 
			em.persist(item);
		em.getTransaction().commit();
		em.close();
		
		return item;
	}
	
	//Returns the libraryItem by id
	@Override
	public LibraryItem readById(int id) {
		
		EntityManager em = connection.getEntityManager();
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findByID");
		q.setParameter("itemID", id);
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items.get(0);
	}
	
	
	//Returns a list of all library items in database
	@Override
	public List<LibraryItem> readAll() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findAll");
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}
	
	//Updates/modifies library item t
	@Override
	public void update(LibraryItem item) {
		EntityManager em = connection.getEntityManager();
		LibraryItem foundItem = em.find(LibraryItem.class, item.getItemID());
		em.getTransaction().begin();
		
		foundItem.setAvailable(item.isAvailable());
		
		foundItem.setTitle(item.getTitle());
		foundItem.setDescription(item.getDescription());
		foundItem.setGenre(item.getGenre());
		foundItem.setCreator(item.getCreator());
		foundItem.setPublicationYear(item.getPublicationYear());
		foundItem.setImgSrc(foundItem.getImgSrc());
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	//Deletes libraryItem from database
	@Override
	public void delete(LibraryItem item) {
		EntityManager em = connection.getEntityManager();
		LibraryItem foundItem = em.find(LibraryItem.class, item.getItemID());
		
		em.getTransaction().begin();
		em.remove(foundItem);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public List<LibraryItem> readByTitle(String title) {
		EntityManager em = connection.getEntityManager();
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findByTitle");
		q.setParameter("title", title);
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public List<LibraryItem> readByCreator(String creator) {
		EntityManager em = connection.getEntityManager();
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findByCreator");
		q.setParameter("creator", creator);
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public List<LibraryItem> readAllEbooks() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findAllEbooks");
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public List<LibraryItem> readAllAudioBooks() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findAllAudioBooks");
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public List<LibraryItem> readAllMagazines() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findAllMagazines");
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public List<LibraryItem> readAllVideos() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findAllVideos");
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public List<LibraryItem> readByType(Class type) {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findByType");
		q.setParameter("type", type);
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public List<LibraryItem> readAllAvailable() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<LibraryItem> q = (TypedQuery<LibraryItem>) em.createNamedQuery("library.findAllAvailable");
		List<LibraryItem> items = q.getResultList();
		em.close();
		
		return items;
	}

}
