package com.packt.hibernate.ch3.listing02;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car implements Transportation {

	@Id
	@GeneratedValue
	private long id;
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
				+ dropOffLocation  + "]";
	}

	@Override
	public double calculateCost() {
		// do some calculation
		return 350.00;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dropOffLocation == null) ? 0 : dropOffLocation.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((pickupLocation == null) ? 0 : pickupLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (dropOffLocation == null) {
			if (other.dropOffLocation != null)
				return false;
		} else if (!dropOffLocation.equals(other.dropOffLocation))
			return false;
		if (id != other.id)
			return false;
		if (pickupLocation == null) {
			if (other.pickupLocation != null)
				return false;
		} else if (!pickupLocation.equals(other.pickupLocation))
			return false;
		return true;
	}

	
}
