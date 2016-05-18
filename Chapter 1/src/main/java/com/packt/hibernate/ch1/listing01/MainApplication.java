package com.packt.hibernate.ch1.listing01;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApplication {
	
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
	
	public static int randomNumberBetween(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min; 
	}
	
	public static Date randomBirthdate() {
        GregorianCalendar calendar = new GregorianCalendar();
        int year = randomNumberBetween(1960, 1999);
        int day = randomNumberBetween(1, calendar.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        calendar.set(GregorianCalendar.YEAR, year);
        calendar.set(GregorianCalendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}
	
	public static String randomSsn() {
		int first = randomNumberBetween(100, 999);
		int second = randomNumberBetween(10, 99);
		int third = randomNumberBetween(1000, 9999);
		
		return first + "-" + second + "-" + third;
	}
	public static Person randomPerson() {
		Person person = new Person();
		person.setFirstname(firstnames[randomNumberBetween(0, firstnames.length)]);
		person.setLastname(lastnames[randomNumberBetween(0, lastnames.length)]);
		person.setBirthdate(randomBirthdate());
		person.setSsn(randomSsn());
		return person;
	}

	public static void main(String[] args) {
				
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
							
			try {
				for (int i=0; i < 20; i++) {
					Person person = randomPerson();										
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
			
			System.out.println("*** fetching all persons");

			// start a new session.			
			session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			transaction = session.beginTransaction();
			try {
				Query query = session.createQuery("from Person");
				
				List<Person> persons = query.list();
				
				for (Person person: persons) {
					System.out.println("Fetching from persistent context using person ID: " + person.getId());
					Person newPerson = (Person) session.get(Person.class, person.getId());

					// modifying an entity and then setting it back to original 
					// value will not mark the entity as dirty.
					
//					String orig = newPerson.getFirstname();
//					newPerson.setFirstname("foo");
//					newPerson.setFirstname(orig);
					
					// check logs to see if we get SQL hits
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
