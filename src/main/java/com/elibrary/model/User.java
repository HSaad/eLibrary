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
	@NamedQuery(name = "User.findAllOrderedDescending", query = "SELECT u FROM User u ORDER BY u.firstName DESC"),
	//find All users
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	//find user by id
	//@NamedQuery(name = "user.findByID", query = "SELECT u FROM User u WHERE u.id = :id"),
	//find user by username
	@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
	//find user by email
	@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String firstName;
    private String lastName;
    private String username; 
    private String email;
    
    @Column(name="pw")
	private String password;
    
    public User() {}

	public User(Long id, String username, String password, String email, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}    

	public User(String username, String password, String email, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [userID=" + id + ", username=" + username + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = (int) (prime * result + id);
//		result = prime * result + ((username == null) ? 0 : username.hashCode());
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
