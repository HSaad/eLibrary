package com.elibrary.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value = "Librarian")
public class Librarian extends User_JPA {

}
