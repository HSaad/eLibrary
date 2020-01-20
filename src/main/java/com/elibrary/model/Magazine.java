package com.elibrary.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "Magazine")
public class Magazine extends LibraryItem{
	private String frequency; 
	private String publisher;
	private String edition;
	private String format;
	
	public String getFrequency() {
		return frequency;
	}
	
	public Magazine setFrequency(String frequency) {
		this.frequency = frequency;
		return this;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public Magazine setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public Magazine setEdition(String edition) {
		this.edition = edition;
		return this;
	}
	
	public String getFormat() {
		return format;
	}
	
	public Magazine setFormat(String format) {
		this.format = format;
		return this;
	}
}
