package com.elibrary.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "Borrower")
public class Borrower extends User{

	public Borrower() {
		super();
	}

}
