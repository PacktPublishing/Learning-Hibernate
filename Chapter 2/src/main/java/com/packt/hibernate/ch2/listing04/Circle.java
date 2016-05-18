package com.packt.hibernate.ch2.listing04;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Circle {

	@Id
	@GeneratedValue
	private long id;
	private float radius;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Center center;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

}
