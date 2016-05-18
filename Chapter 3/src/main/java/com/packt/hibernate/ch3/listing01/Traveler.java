package com.packt.hibernate.ch3.listing01;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Traveler {
	@Id
	@GeneratedValue
	private long id;
	
	private String firstname;
	private String lastname;
	
	@ManyToAny(metaColumn=@Column(name="dtype"))
	private Set<Transportation> transportation = new HashSet<Transportation>();

	// getters and setters
	
	
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

	public Set<Transportation> getTransportation() {
		return transportation;
	}

	public void setTransportation(Set<Transportation> transportation) {
		this.transportation = transportation;
	}	
}
