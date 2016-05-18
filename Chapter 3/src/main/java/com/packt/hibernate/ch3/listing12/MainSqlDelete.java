package com.packt.hibernate.ch3.listing12;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainSqlDelete {

	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				Person person = (Person) session.get(Person.class, new Long(1));
				if (person != null) {
					System.out.println("deleting: " + person);
					session.delete(person);
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateUtil.stopService();
	}

}
