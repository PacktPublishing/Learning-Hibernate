package com.packt.hibernate.ch3.listing08;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainCheck {

	public static void main(String[] args) {
		Item item = new Item();
		item.setDescription("MacBook Pro");
		item.setPrice(1000);
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				session.save(item);
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
