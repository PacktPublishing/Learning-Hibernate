package com.packt.hibernate.ch3.check.immutable;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FilterMain {

	public static void main(String[] args) {
		try {

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				CourseImmutable course = (CourseImmutable) session.get(CourseImmutable.class, new Long(1));
				course.setTitle("change me");
//				Student student = new Student();
//				student.setGender('M');
//				student.setName("John King");
//				course.getStudents().add(student);
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateUtil.stopService();
	}

}
