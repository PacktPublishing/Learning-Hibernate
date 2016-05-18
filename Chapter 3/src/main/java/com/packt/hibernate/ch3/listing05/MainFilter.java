package com.packt.hibernate.ch3.listing05;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainFilter {

	public static void main(String[] args) {
		try {

			Course course = new Course();
			course.setTitle("Chemistry 101");
			
			Student student = new Student();
			student.setName("David Smith");
			student.setGender('M');
			course.getStudents().add(student);
			
			student = new Student();
			student.setName("Susan Smith");
			student.setGender('F');
			course.getStudents().add(student);

			student = new Student();
			student.setName("Kelly Johnson");
			student.setGender('F');
			course.getStudents().add(student);
			
			student = new Student();
			student.setName("Martha Stewart");
			student.setGender('F');
			course.getStudents().add(student);
			
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			Long courseId = null;
			
			try {
				courseId = (Long) session.save(course);

				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			} finally {
				if (session.isOpen())
					session.close();
			}
			
			session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			transaction = session.beginTransaction();
			try {
				Filter filter = session.enableFilter("genderFilter");
				filter.setParameter("gender", 'F');
				Course course2 = (Course) session.get(Course.class, courseId);
				Set<Student> students = course2.getStudents();
				
				Iterator<Student> it = students.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
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
