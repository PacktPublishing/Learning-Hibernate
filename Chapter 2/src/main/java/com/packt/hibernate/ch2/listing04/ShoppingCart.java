package com.packt.hibernate.ch2.listing04;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.GenericGenerator (
		name= "custom-generator", 
		strategy="sequence"
)
public class ShoppingCart {

	@Id
	@GeneratedValue(generator="custom-generator")
	private long id;
	private Date createdTime;

	// getters and setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
