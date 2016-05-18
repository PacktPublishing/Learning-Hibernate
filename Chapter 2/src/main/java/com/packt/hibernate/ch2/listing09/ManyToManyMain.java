package com.packt.hibernate.ch2.listing09;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManyToManyMain {

	public static void main(String[] args) {

		Teacher teacher1 = new Teacher();
		teacher1.setFirstname("John");
		teacher1.setLastname("Smith");
		
		Teacher teacher2 = new Teacher();
		teacher2.setFirstname("Sara");
		teacher2.setLastname("Brown");
		
		Student student1 = new Student();
		student1.setFirstname("Kevin");
		student1.setLastname("King");
		
		Student student2 = new Student();
		student2.setFirstname("Gail");
		student2.setLastname("Jones");
		
		Collection<Student> students = new ArrayList<Student>();
		students.add(student1);
		students.add(student2);
		
		teacher1.setStudents(students);
		teacher2.setStudents(students);
		
		Collection<Teacher> teachers = new ArrayList<Teacher>();
 		teachers.add(teacher1);
 		teachers.add(teacher2);
 		
 		student1.setTeachers(teachers);
 		student2.setTeachers(teachers);
 		
		try {
			Session session = HibernateUtil
					.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(teacher1);
				session.save(teacher2);
				session.save(student1);
				session.save(student2);
				
				transaction.commit();
			}
			catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			}
			finally {
				if (session.isOpen())
					session.close();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		HibernateUtil.stopService();
	}

}
