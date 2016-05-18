package com.packt.hibernate.ch4.listing01;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainFetchJoin {

	public static void main(String[] args) {
		try {

			Course chemistry = new Course();
			chemistry.setTitle("Chemistry 101");
			
			Student david = new Student();
			david.setName("David Smith");
			david.setGender('M');
			
			Student susan = new Student();
			susan.setName("Susan Smith");
			susan.setGender('F');

			Student kelly = new Student();
			kelly.setName("Kelly Johnson");
			kelly.setGender('F');
			
			Student martha = new Student();
			martha.setName("Martha Stewart");
			martha.setGender('F');
			
			chemistry.getStudents().add(david);
			chemistry.getStudents().add(susan);
			chemistry.getStudents().add(kelly);
			chemistry.getStudents().add(martha);
			
			david.setCourse(chemistry);
			susan.setCourse(chemistry);
			kelly.setCourse(chemistry);
			martha.setCourse(chemistry);
			
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
				
			Long chemistryId = null;
			
			try {
				chemistryId = (Long) session.save(chemistry);
				
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			} finally {
				if (session.isOpen())
					session.close();
			}
			
			System.out.println("*** fetching the data");

			session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			transaction = session.beginTransaction();
			try {
				Course course = (Course) session.get(Course.class, chemistryId);
				
				System.out.println(course);
//					for (Student student:course.getStudents()) {
//						System.out.println("--- " + student);
//					}
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
