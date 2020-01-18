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
@Table(name="LibraryItem")
//@DiscriminatorColumn(name = "item_type", length=20)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
	//find all library items
	@NamedQuery(name = "library.findAll", query = "SELECT i FROM LibraryItem i"),
	//find library item by itemID
	@NamedQuery(name = "library.findByID", query = "SELECT i FROM LibraryItem i WHERE i.itemID = :itemID"),
	//find library item by title
	@NamedQuery(name = "library.findByTitle", query = "SELECT i FROM LibraryItem i WHERE UPPER(i.title) LIKE CONCAT('%',UPPER(:title),'%') "),
	//find all library items by author/creator
	@NamedQuery(name = "library.findByCreator", query = "SELECT i FROM LibraryItem i WHERE i.creator = :creator"),
	//find all library items that are available (true)
	@NamedQuery(name = "library.findAllAvailable", query = "SELECT i FROM LibraryItem i WHERE i.available = 1"),
	
	//find all ebooks
	@NamedQuery(name = "library.findAllEbooks", query = "SELECT i from LibraryItem i WHERE TYPE(i) = Ebook"),
	//find all audiobooks
	@NamedQuery(name = "library.findAllAudioBooks", query = "SELECT i from LibraryItem i WHERE TYPE(i) = AudioBook"),
	//find all magazines
	@NamedQuery(name = "library.findAllMagazines", query = "SELECT i from LibraryItem i WHERE TYPE(i) = Magazine"),
	//find all videos
	@NamedQuery(name = "library.findAllVideos", query = "SELECT i from LibraryItem i WHERE TYPE(i) = Video"),
	//Find library items by type
	@NamedQuery(name = "library.findByType", query = "SELECT i from LibraryItem i WHERE TYPE(i) = :type"),
})
public class LibraryItem implements IStorable{
	
	@Id
	@Column(name="itemID")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="itemID") //fix id generation (continues from other table sequence)
	@SequenceGenerator(name="itemID", sequenceName="itemID")
	private int itemID;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="creator")
	private String creator;
	
	@Column(name="description")
	private String description;
	
	@Column(name="dateAdded")
	private LocalDate dateAdded;//LocalDate dateAdded;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="publicationYear")
	private int publicationYear;
	
	@Column(name="imgSrc", nullable=false)
	private String imgSrc;
	
	@Column(columnDefinition = "Number(1)")
	private boolean available;
	
	public LibraryItem() {
		super();
	}
	
	public LibraryItem(int itemID, String title, String description, LocalDate dateAdded, String genre, String creator, boolean available) {
		super();
		this.itemID = itemID;
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

	public int getItemID() {
		return itemID;
	}
	
	public LibraryItem setItemID(int itemID) {
		this.itemID = itemID;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemID;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
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
		if (itemID != other.itemID)
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
		return "LibraryItem [itemID=" + itemID + ", title=" + title + ", description=" + description + ", dateAdded="
				+ dateAdded + ", genre=" + genre + "]";
	}
	
}
