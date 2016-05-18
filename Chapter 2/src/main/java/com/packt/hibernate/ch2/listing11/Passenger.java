package com.packt.hibernate.ch2.listing11;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class Passenger extends Person {
	@Column(name="DEST")
	private String destination;
	
	
	// getters and setters
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
