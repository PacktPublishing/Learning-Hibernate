package com.packt.hibernate.ch4.listing04;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainHqlFetch {
	
	public static final String[] firstnames = {
		"John",
		"Steve",
		"Kevin",
		"Jill",
		"Sara",
		"Robert",
		"Dillon",
		"Elizabeth",
		"Mary",
		"Maria",
		"Sherry"
	};

	public static final String[] lastnames = {
		"Smith",
		"Robertson",
		"Johnson",
		"Dillon",
		"Stein",
		"Morgan",
		"Duncan",
		"Madison",
		"Ashcroft",
		"Shield",
		"Weinstein"
	};

	public static final String[] street = {
		"Main Street",
		"Eastern Avenue",
		"Maple Avenue",
		"Park Avenue",
		"Wisconsin Avenue",
		"Elm Street",
		"Willow Lane"
	};

	public static final String[] city = {
		"Washington",
		"Fairfax",
		"Arlington",
		"Alexandria",
		"Falls Church"
	};
	
	public static Address randomAddress() {
		Address address = new Address();
		Random rand2 = new Random();
		int streetLine = rand2.nextInt(street.length);
		int cityLine =  rand2.nextInt(city.length);
		
		Random house = new Random();
		int houseNumber = house.nextInt(1000 - 500 + 1) + 500;
		address.setStreet(houseNumber + " " + street[streetLine]);
		address.setCity(city[cityLine]);
		
		return address;
	}

	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
							
			try {
				for (int i=0; i < 50; i++) {
					Person person = new Person();
					Random rand = new Random();
					int first = rand.nextInt(firstnames.length);
					int last =  rand.nextInt(lastnames.length);
					person.setFirstname(firstnames[first]);
					person.setLastname(lastnames[last]);

					Address address = randomAddress();
					address.setPerson(person);
					person.getAddresses().add(address);
					
					address = randomAddress();
					address.setPerson(person);
					person.getAddresses().add(address);
					
					session.save(person);
				}
				
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			} finally {
				if (session.isOpen())
					session.close();
			}
			
			System.out.println("*** fetching the data");

			session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			transaction = session.beginTransaction();
			try {
				List<Person> persons = session
						.createQuery("from Person p left join fetch p.addresses a where a.city = 'Fairfax'")
						.list();
				for (Person person:persons) {
//					Person person = (Person) object;
					System.out.println(person);
					for (Address address: person.getAddresses()) {
						System.out.println(address);
					}
				}
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
//				// log stack trace
				e.printStackTrace();

			} finally {
				if (session.isOpen())
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateUtil.stopService();
	}

}
