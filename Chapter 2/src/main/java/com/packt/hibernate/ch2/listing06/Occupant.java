package com.packt.hibernate.ch2.listing06;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Occupant {
	// @Id
	// @GeneratedValue
	// private RoomId roomId;

	@Id
	private RoomId roomId;

	private Date checkinDate;
	private Date checkoutDate;

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public RoomId getRoomId() {
		return roomId;
	}

	public void setRoomId(RoomId roomId) {
		this.roomId = roomId;
	}


}
