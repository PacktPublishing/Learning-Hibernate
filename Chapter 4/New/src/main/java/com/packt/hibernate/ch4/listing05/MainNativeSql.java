package com.packt.hibernate.ch4.listing05;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

public class MainNativeSql {
	
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
	
	public static Address randomAddress() {
		Address address = new Address();
		int streetIndex = randomNumberBetween(0, street.length);
		int cityIndex =  randomNumberBetween(0, city.length);		
		int houseNumber = randomNumberBetween(500, 1000);
		
		address.setStreet(houseNumber + " " + street[streetIndex]);
		address.setCity(city[cityIndex]);
		
		return address;
	}

	public static Date randomBirthdate() {
        GregorianCalendar calendar = new GregorianCalendar();
        int year = randomNumberBetween(1960, 1999);
        int day = randomNumberBetween(1, calendar.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        calendar.set(GregorianCalendar.YEAR, year);
        calendar.set(GregorianCalendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}
	
	public static Person randomPerson() {
		Person person = new Person();
		person.setFirstname(firstnames[randomNumberBetween(0, firstnames.length)]);
		person.setLastname(lastnames[randomNumberBetween(0, lastnames.length)]);
		person.setBirthdate(randomBirthdate());
		return person;
	}

	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
							
			try {
				for (int i=0; i < 10; i++) {
					Person person = randomPerson();

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
				List<Object[]> objectList = session
						.createSQLQuery("SELECT * FROM PERSON")
						.addScalar("ID", LongType.INSTANCE)
						.addScalar("FIRSTNAME", StringType.INSTANCE)
						.addScalar("LASTNAME", StringType.INSTANCE)
						.addScalar("BIRTHDATE", DateType.INSTANCE)
						.list();
				for(Object[] objects: objectList) {
					Long id = (Long) objects[0];
					String firstname = (String) objects[1];
					String lastname = (String) objects[2];
					Date birthdate = (Date) objects[3];

					System.out.println(id + "|" + firstname + "|" + lastname + "|" + birthdate);
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
