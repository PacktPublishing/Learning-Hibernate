package com.packt.hibernate.ch3.listing06;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainNotFoundCreate {

	public static void main(String[] args) {
		try {
			
			Course course = new Course();
			course.setTitle("Chemistry 101");
			
			StudentEnrollment enrollment1 = new StudentEnrollment();

			course.getStudents().add(enrollment1);
			enrollment1.setCourse(course);
			
			StudentEnrollment enrollment2 = new StudentEnrollment();

			course.getStudents().add(enrollment2);
			enrollment2.setCourse(course);
			
			StudentEnrollment enrollment3 = new StudentEnrollment();

			course.getStudents().add(enrollment3);
			enrollment3.setCourse(course);
			
			Course course2 = new Course();
			course2.setTitle("Physics 101");
			

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				session.save(course);
				session.save(course2);
				session.save(enrollment1);
				session.save(enrollment2);
				session.save(enrollment3);
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
