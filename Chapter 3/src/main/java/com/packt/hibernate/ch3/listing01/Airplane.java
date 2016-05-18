package com.packt.hibernate.ch3.listing01;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="A")
public class Airplane extends Transportation {

	private String departureAirport;
	private String arrivalAirport;

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	@Override
	public String toString() {
		return "Airplane [departureAirport=" + departureAirport
				+ ", arrivalAirport=" + arrivalAirport + ", cost=" + getCost() + "]";
	}

}
