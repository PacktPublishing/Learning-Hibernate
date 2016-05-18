package com.packt.hibernate.ch2.listing11;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Driver extends Person {

	@Column(name="LIC_NUM")
	private String licenseNumber;
	
	// getters and setters
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
}
