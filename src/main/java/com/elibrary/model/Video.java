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
}
