package com.packt.hibernate.ch2.listing04;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class IdGenerationHibernateMain {

	public static void main(String[] args) {
		try {
			ShoppingCart cart = new ShoppingCart();
			cart.setCreatedTime(new Date());
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(cart);

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
