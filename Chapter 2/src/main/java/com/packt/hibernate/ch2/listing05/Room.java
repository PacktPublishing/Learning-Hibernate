package com.packt.hibernate.ch2.listing05;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Room implements Serializable {

	// @Id
	// private long roomNumber;
	// @Id
	// private long buildingNumber;
	@EmbeddedId
	private RoomId id;
	private double length;
	private double width;


	public RoomId getId() {
		return id;
	}

	public void setId(RoomId id) {
		this.id = id;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
}
