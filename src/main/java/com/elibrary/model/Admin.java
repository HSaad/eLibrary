package com.elibrary.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "Admin")
public class Admin extends User{
	private String privilege;
	
	public String getPrivilege() {
		return this.privilege;
	}
	
	public Admin setPrivilege(String privilege) {
		this.privilege = privilege;
		return this;
	}
}
