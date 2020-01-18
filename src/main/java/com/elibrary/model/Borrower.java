package com.elibrary.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "Borrower")
public class Borrower extends User_JPA{

	public Borrower() {
		super();
	}

}
