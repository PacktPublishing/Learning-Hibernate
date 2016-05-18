package com.packt.hibernate.ch3.listing10;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private long id;
	@ColumnTransformer(read=("upper(firstname)"), write=("upper(?)"))
	private String firstname;
	@ColumnTransformer(read=("upper(lastname)"), write=("upper(?)"))
	private String lastname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + "]";
	}

}
