package com.elibrary.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "AudioBook")
public class AudioBook extends LibraryItem{
	private int fileSize;
	private String duration;
	private String publisher;
	private String narrator;
	private String author;
	private String format;
	
	public int getFileSize() {
		return fileSize;
	}
	
	public AudioBook setFileSize(int fileSize) {
		this.fileSize = fileSize;
		return this;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public AudioBook setDuration(String duration) {
		this.duration = duration;
		return this;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public AudioBook setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}
	
	public String getNarrator() {
		return narrator;
	}
	
	public AudioBook setNarrator(String narrator) {
		this.narrator = narrator;
		return this;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public AudioBook setAuthor(String author) {
		this.author = author;
		return this;
	}
	
	public String getFormat() {
		return format;
	}
	
	public AudioBook setFormat(String format) {
		this.format = format;
		return this;
	}
}
