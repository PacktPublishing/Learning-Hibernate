package com.packt.hibernate.ch2.listing11;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InheritanceMain {

	public static void main(String[] args) {
		try {
			
			Person person = new Person();
			person.setFirstname("Ramin");
			person.setLastname("Rad");
			
			Driver driver = new Driver();
			driver.setFirstname("John");
			driver.setLastname("King");
			driver.setLicenseNumber("5-23112");
			
			Passenger passenger = new Passenger();
			passenger.setFirstname("Sara");
			passenger.setLastname("Davis");
			passenger.setDestination("NY");
			
			Session session = HibernateUtil
					.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(person);
				session.save(driver);
				session.save(passenger);
				
				transaction.commit();
			}
			catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			}
			finally {
				if (session.isOpen())
					session.close();
			}
			
			session = HibernateUtil
					.getSessionFactory()
					.getCurrentSession();
			transaction = session.beginTransaction();
			try {
				Person person2 = (Person) session.get(Person.class,	new Long(3));
				
				if (person2 instanceof Passenger) {
					System.out.println("Passenger");
				}
				transaction.commit();
			}
			catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			}
			finally {
				if (session.isOpen())
					session.close();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		HibernateUtil.stopService();
	}

}
