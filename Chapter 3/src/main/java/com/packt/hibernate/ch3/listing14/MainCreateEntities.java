package com.packt.hibernate.ch3.listing14;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainCreateEntities {

	public static void main(String[] args) {
		Person person = new Person();
		person.setFirstname("James");
		person.setLastname("Dean");
		person.getAliases().add("Jimmie");
		person.getAliases().add("Jim");
		
		Address home = new Address();
		home.setStreet("123 Main Street");
		home.setCity("Fairfax");
		home.setCountry("USA");
		
		Address work = new Address();
		work.setStreet("55 Elmwood Ave");
		work.setCity("Reston");
		work.setCountry("USA");
		
		person.getAddresses().add(home);
		person.getAddresses().add(work);
		
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				session.save(person);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				// log stack trace
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
