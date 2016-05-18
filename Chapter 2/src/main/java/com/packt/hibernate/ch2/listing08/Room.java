package com.packt.hibernate.ch2.listing08;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room implements Serializable {

	@Id
	private RoomId id;
	private double length;
	private double width;
	@OneToMany(cascade=CascadeType.ALL)
	private Collection<Occupant> occupants;
	
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

	public Collection<Occupant> getOccupants() {
		return occupants;
	}

	public void setOccupants(Collection<Occupant> occupants) {
		this.occupants = occupants;
	}
}
