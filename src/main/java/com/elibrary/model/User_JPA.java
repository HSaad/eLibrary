package com.elibrary.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="User_JPA")
@DiscriminatorColumn(name = "user_type", length=20)//discriminatorType = DiscriminatorType.STRING, length = 20)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
	
	//find all users
	@NamedQuery(name = "user.findAll", query = "SELECT u FROM User_JPA u"),
	//find user by userid
	@NamedQuery(name = "user.findByID", query = "SELECT u FROM User_JPA u WHERE u.userID = :userID"),
	//find user by username
	@NamedQuery(name = "user.findByUsername", query = "SELECT u FROM User_JPA u WHERE u.username = :username"),
	//find user by email
	@NamedQuery(name = "user.findByEmail", query = "SELECT u FROM User_JPA u WHERE u.email = :email"),
	//find all admins
	@NamedQuery(name = "user.findAllAdmins", query = "SELECT u from User_JPA u WHERE TYPE(u) = Admin"),
	//find all borrowers
	@NamedQuery(name = "user.findAllBorrowers", query = "SELECT u from User_JPA u WHERE TYPE(u) = Borrower"),
	//find all librarians
	@NamedQuery(name = "user.findAllLibrarians", query = "SELECT u from User_JPA u WHERE TYPE(u) = Librarian"),
	//Find users by type
	@NamedQuery(name = "user.findByType", query = "SELECT u from User_JPA u WHERE TYPE(u) = :type"),
})
public class User_JPA implements IStorable{
	
	@Id
	@Column(name="userID")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="userID") //fix id generation (continues from other table sequence)
	@SequenceGenerator(name="userID", sequenceName="userID")
	private int userID;
	
	@Column(name="username", nullable=false, unique=true)
	private String username; 
	
	@Column(name="pw", nullable=false, length=30)
	private String password;
	
//	@Column(name="libraryCard", nullable=false, unique=true)
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private int libraryCard;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	/* @Embedded
	 * private UserProfile profile;
	 */
	
	public User_JPA() {
		super();
	}

	public User_JPA(int userID, String username, String password, String email, String firstName,
			String lastName) {
		super();
		this.username = username;
		this.password = password;
		//this.libraryCard = libraryCard;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User_JPA(String username, String password, String email, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	//Delete? Don't need to be able to set ID
	public User_JPA setUserID(int userID) {
		this.userID = userID;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User_JPA setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User_JPA setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User_JPA setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User_JPA setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User_JPA setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
//	public int getLibraryCard() {
//		return this.libraryCard;
//	}
//	
//	public User_JPA setLibraryCard(int libraryCard) {
//		this.libraryCard = libraryCard;
//		return this;
//	}
//	
	
	@Override
	public String toString() {
		return "User_JPA [userID=" + userID + ", username=" + username + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + userID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User_JPA other = (User_JPA) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userID != other.userID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
