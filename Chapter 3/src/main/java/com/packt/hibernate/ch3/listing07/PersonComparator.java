package com.packt.hibernate.ch3.listing07;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		if (p1.getLastname().equals(p2.getLastname())) {
			return p1.getFirstname().compareTo(p2.getFirstname());
		}
		return p1.getLastname().compareTo(p2.getLastname());
	}
}
