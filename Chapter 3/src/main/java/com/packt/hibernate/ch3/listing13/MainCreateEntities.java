package com.packt.hibernate.ch3.listing13;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainCreateEntities {

	public static void main(String[] args) {
		Person person = new Person();
		person.setFirstname("James");
		person.setLastname("Dean");

		Language l1 = new Language();
		l1.setName("Java");
		l1.setYear(2015);
		l1.setPerson(person);
		
		Language l2 = new Language();
		l2.setName("PHP");
		l2.setYear(2014);
		l2.setPerson(person);
		
		Language l3 = new Language();
		l3.setName("Ruby");
		l3.setYear(2013);
		l3.setPerson(person);
		
		Language l4 = new Language();
		l4.setName("Java");
		l4.setYear(2012);
		l4.setPerson(person);
		
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				session.save(person);
				session.save(l1);
				session.save(l2);
				session.save(l3);
				session.save(l4);
				
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
