package com.packt.hibernate.ch3.listing01;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="C")
public class Car extends Transportation {

	private String pickupLocation;
	private String dropOffLocation;

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDropOffLocation() {
		return dropOffLocation;
	}

	public void setDropOffLocation(String dropOffLocation) {
		this.dropOffLocation = dropOffLocation;
	}

	@Override
	public String toString() {
		return "Car [pickupLocation=" + pickupLocation + ", dropOffLocation="
				+ dropOffLocation + ", cost=" + getCost() + "]";
	}

	
}
