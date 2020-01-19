package com.example;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="cuser")
@NamedQuery(name = "User.findAllOrderedByNameDescending", query = "SELECT u FROM User u ORDER BY u.first DESC")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	private String first;
    private String last;
    private String email;
    private String city;
    private String company;

    public User() {}

    public User(Long id, String first, String last, String email, String city, String company) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.email = email;
        this.city = city;
        this.company = company;
    }

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
}
