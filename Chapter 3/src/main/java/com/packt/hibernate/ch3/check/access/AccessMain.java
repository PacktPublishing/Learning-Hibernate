package com.packt.hibernate.ch3.check.access;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccessMain {

	public static void main(String[] args) {
		try {

			Course course = new Course();
			course.setTitle("Math 101");
//			course.setId(101);
						
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(course);

				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			} finally {
				if (session.isOpen())
					session.close();
			}
			
			Thread.sleep(3000);
			
			course.setTitle("Calculus 101");
			session = HibernateUtil.getSessionFactory()
					.openSession();
			transaction = session.beginTransaction();
			try {
				session.update(course);

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
