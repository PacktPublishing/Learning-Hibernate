package com.packt.hibernate.ch5.listing03;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

public class MainQueryCache {
	
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

			// populate the database
			try {
				for (int i=0; i < 100; i++) {
					Person person = randomPerson();										
					session.save(person);
				}
				
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			} finally {
				if (session.isOpen())
					session.close();
			}
			
			System.out.println("*** fetching all persons whose first name begins with 'J'");

			
			// keep a list of all person IDs
			List<Long> personIdList = new ArrayList<Long>();
			
			// start a new session.
			
			session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			transaction = session.beginTransaction();
			try {
				Query query = session.createQuery("from Person where firstname like 'J%'")
						.setCacheable(true);
				
				List<Person> persons = query.list();
				
				for (Person person: persons) {
					personIdList.add(person.getId());
				}
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			} finally {
				if (session.isOpen())
					session.close();
			}
			
			// start a new session to check second level cache hits.
			
			session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			transaction = session.beginTransaction();
			try {				

				// randomly get one of the persons we fetched with query.
				Person newPerson = (Person) session.get(Person.class, personIdList.get(randomNumberBetween(0, personIdList.size() - 1)));
				System.out.println("fetching by ID: " + newPerson);

				
				Query query = session.createQuery("from Person where firstname like 'J%'")
						.setCacheable(true);
				
				List<Person> persons = query.list();
				
				for (Person person: persons) {
					System.out.println(person);
				}
				
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			} finally {
				if (session.isOpen())
					session.close();
			}
			
			Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
			for(String regionName: stats.getSecondLevelCacheRegionNames()) {
				System.out.println("cache region: " + regionName);				
			}
			System.out.println("stats - second level cache puts: " + stats.getSecondLevelCachePutCount());
			System.out.println("stats - second level cache hits: " + stats.getSecondLevelCacheHitCount());
			System.out.println("stats - second level cache miss: " + stats.getSecondLevelCacheMissCount());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateUtil.stopService();
	}

}
