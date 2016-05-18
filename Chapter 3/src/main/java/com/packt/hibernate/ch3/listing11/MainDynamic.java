package com.packt.hibernate.ch3.listing11;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainDynamic {

	public static void main(String[] args) {
		Person person = new Person();
		person.setFirstname("James");
		
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
