package com.packt.hibernate.ch3.listing04;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainMapsId {

	public static void main(String[] args) {
		try {

			Course course = new Course();
			course.setTitle("Chemistry 101");
			
			Student student = new Student();
			student.setName("David Smith");
			
			Enrolment enrollment = new Enrolment();
			enrollment.setGrade("A-");
			enrollment.setStudent(student);
			enrollment.setCourse(course);
			
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(student);
				session.save(course);
				session.save(enrollment);

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
