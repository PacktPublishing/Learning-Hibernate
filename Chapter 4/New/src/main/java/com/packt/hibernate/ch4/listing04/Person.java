package com.packt.hibernate.ch4.listing04;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Person {
	@Id
	@GeneratedValue
	private long id;
	private String firstname;
	private String lastname;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="person", fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	Set<Address> addresses = new HashSet<Address>();
	
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

	
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + "]";
	}

	
}
