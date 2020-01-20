package com.elibrary.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "Book")
public class Ebook extends LibraryItem{
	private int fileSize;
	private String publisher;
	private String author;
	private String format;
	
	public Ebook() {
	}

	public int getFileSize() {
		return fileSize;
	}
	
	public Ebook setFileSize(int fileSize) {
		this.fileSize = fileSize;
		return this;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public Ebook setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Ebook setAuthor(String author) {
		this.author = author;
		return this;
	}
	
	public String getFormat() {
		return format;
	}
	
	public Ebook setFormat(String format) {
		this.format = format;
		return this;
	}
	
}
