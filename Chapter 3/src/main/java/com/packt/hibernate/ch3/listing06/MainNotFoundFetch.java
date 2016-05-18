package com.packt.hibernate.ch3.listing06;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainNotFoundFetch {

	public static void main(String[] args) {
		try {

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				StudentEnrollment enrollment = (StudentEnrollment) session.get(StudentEnrollment.class, new Long(3));
				Course course = enrollment.getCourse();
				if (course == null) {
					System.out.println("No course found for this enrollment!");
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
