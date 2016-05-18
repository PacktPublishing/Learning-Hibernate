package com.packt.hibernate.ch2.listing05;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RoomId implements Serializable {
	private long roomNumber;
	private long buildingNumber;

	// getters and setters
	public long getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(long roomNumber) {
		this.roomNumber = roomNumber;
	}

	public long getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(long buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (buildingNumber ^ (buildingNumber >>> 32));
		result = prime * result + (int) (roomNumber ^ (roomNumber >>> 32));
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
		RoomId other = (RoomId) obj;
		if (buildingNumber != other.buildingNumber)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

}
