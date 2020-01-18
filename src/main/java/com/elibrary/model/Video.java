package com.elibrary.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "Video")
public class Video extends LibraryItem{
	private String publisher;
	private String duration;
	//private List<String> creators = new ArrayList<String>();
	
	public String getPublisher() {
		return publisher;
	}
	
	public Video setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public Video setDuration(String duration) {
		this.duration = duration;
		return this;
	}
//	
//	public List<String> getCreators() {
//		return creators;
//	}
//	
//	public Video setCreators(List<String> creators) {
//		this.creators = creators;
//		return this;
//	}
//	
	
}
