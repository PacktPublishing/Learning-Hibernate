package com.packt.hibernate.ch3.listing09;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainColumnDefault {

	public static void main(String[] args) {
		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("Washington");

		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				session.save(address);
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
