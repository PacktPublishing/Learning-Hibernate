package com.packt.hibernate.ch3.listing02;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Airplane implements Transportation {

	@Id
	@GeneratedValue
	private long id;
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
				+ ", arrivalAirport=" + arrivalAirport + "]";
	}

	@Override
	public double calculateCost() {
		// do some calculation
		return 500.00;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalAirport == null) ? 0 : arrivalAirport.hashCode());
		result = prime
				* result
				+ ((departureAirport == null) ? 0 : departureAirport.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Airplane other = (Airplane) obj;
		if (arrivalAirport == null) {
			if (other.arrivalAirport != null)
				return false;
		} else if (!arrivalAirport.equals(other.arrivalAirport))
			return false;
		if (departureAirport == null) {
			if (other.departureAirport != null)
				return false;
		} else if (!departureAirport.equals(other.departureAirport))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
