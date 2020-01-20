package com.elibrary.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="libitem")
@DiscriminatorColumn(name = "item_type", length=20)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
	//find library item by title
	@NamedQuery(name = "Library.findByTitle", query = "SELECT i FROM LibraryItem i WHERE UPPER(i.title) LIKE CONCAT('%',UPPER(:title),'%') "),
	//find all library items by author/creator
	@NamedQuery(name = "Library.findByCreator", query = "SELECT i FROM LibraryItem i WHERE i.creator = :creator"),
	//find all library items that are available (true)
	@NamedQuery(name = "Library.findAllAvailable", query = "SELECT i FROM LibraryItem i WHERE i.available = TRUE")
	
	//find all ebooks
	//@NamedQuery(name = "library.findAllEbooks", query = "SELECT i from LibraryItem i WHERE TYPE(i) = Ebook"),
	//find all audiobooks
	//@NamedQuery(name = "library.findAllAudioBooks", query = "SELECT i from LibraryItem i WHERE TYPE(i) = AudioBook"),
	//find all magazines
	//@NamedQuery(name = "library.findAllMagazines", query = "SELECT i from LibraryItem i WHERE TYPE(i) = Magazine"),
	//find all videos
	//@NamedQuery(name = "library.findAllVideos", query = "SELECT i from LibraryItem i WHERE TYPE(i) = Video"),
	//Find library items by type
	//@NamedQuery(name = "library.findByType", query = "SELECT i from LibraryItem i WHERE TYPE(i) = :type"),
})
public class LibraryItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String creator;
	private String description;
	private LocalDate dateAdded;
	private String genre;
	private int publicationYear;
	private String imgSrc;
	private boolean available;
	
	public LibraryItem() {
		super();
	}
	
	public LibraryItem(Long id, String title, String description, LocalDate dateAdded, String genre, String creator, boolean available) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.genre = genre;
		this.creator = creator;
		this.available = available;
	}
	
	public LibraryItem (String title, String description, LocalDate dateAdded, String genre, String creator, boolean available) {
		super();
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.genre = genre;
		this.creator = creator;
		this.available = available;
	}
	

	public LibraryItem (String title, String description, LocalDate dateAdded, String genre, String creator, int publicationYear, String imgSrc, boolean available) {
		super();
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.genre = genre;
		this.creator = creator;
		this.publicationYear = publicationYear;
		this.imgSrc = imgSrc;
		this.available = available;
	}
	
	public int getPublicationYear() {
		return publicationYear;
	}

	public LibraryItem setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
		return this;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public LibraryItem setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
		return this;
	}

	public Long getId() {
		return id;
	}
	
	public LibraryItem setId(Long id) {
		this.id = id;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public LibraryItem setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public LibraryItem setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public LocalDate getDateAdded() {
		return dateAdded;
	}
	
	public LibraryItem setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
		return this;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public LibraryItem setGenre(String genre) {
		this.genre = genre;
		return this;
	}
	
	public String getCreator() {
		return creator;
	}

	public LibraryItem setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	public boolean isAvailable() {
		return available;
	}

	public LibraryItem setAvailable(boolean available) {
		this.available = available;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryItem other = (LibraryItem) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LibraryItem [itemID=" + id + ", title=" + title + ", description=" + description + ", dateAdded="
				+ dateAdded + ", genre=" + genre + "]";
	}
	
}
