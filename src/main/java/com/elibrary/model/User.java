package com.elibrary.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="cuser")
@NamedQueries({
	@NamedQuery(name = "User.findAllOrderedDescending", query = "SELECT u FROM User u ORDER BY u.first DESC"),
	//find All users
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	//find user by username
	//@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
	//find user by email
	//@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
	//find all admins
	//@NamedQuery(name = "User.findAllAdmins", query = "SELECT u from User u WHERE TYPE(u) = Admin"),
	//find all borrowers
	//@NamedQuery(name = "User.findAllBorrowers", query = "SELECT u from User u WHERE TYPE(u) = Borrower"),
	//find all librarians
	//@NamedQuery(name = "User.findAllLibrarians", query = "SELECT u from User u WHERE TYPE(u) = Librarian"),
	//Find users by type
	//@NamedQuery(name = "User.findByType", query = "SELECT u from User u WHERE TYPE(u) = :type"),
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String first;
    private String last;
    private String city;
    private String company;

//	private String username; 
//	private String password;
	private String email;
//	private String firstName;
//	private String lastName;
    
    public User() {}

    public User(Long id, String first, String last, String email, String city, String company) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.email = email;
        this.city = city;
        this.company = company;
    }
    

//	public User(String username, String password, String email, String firstName, String lastName) {
//		super();
//		this.username = username;
//		this.password = password;
//		this.email = email;
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}
	

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
//    
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
}
