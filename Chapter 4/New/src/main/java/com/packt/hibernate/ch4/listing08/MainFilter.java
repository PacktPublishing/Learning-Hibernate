package com.packt.hibernate.ch4.listing08;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainFilter {
	
	public static final String[] firstnamesMale = {
		"John",
		"Steve",
		"Kevin",
		"Robert",
		"Dillon"
	};

	public static final String[] firstnamesFemale = {
		"Jill",
		"Sara",
		"Elizabeth",
		"Mary",
		"Maria",
		"Sherry"
	};

	public static int randomNumberBetween(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min; 
	}
	
	public static Student randomStudent() {
		Student student = new Student();
		if (randomNumberBetween(0, 10) >= 5) {
			student.setGender("female");
			student.setName(firstnamesFemale[randomNumberBetween(0, firstnamesFemale.length)]);
		}
		else {
			student.setGender("male");
			student.setName(firstnamesMale[randomNumberBetween(0, firstnamesMale.length)]);
			
		}
		return student;
	}

	public static void main(String[] args) {
		
		Teacher teacher = new Teacher();
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
							
			try {
				
				teacher.setName("Mr. Teacher");

				for (int i=0; i < 50; i++) {
					teacher.getStudents().add(randomStudent());
				}
				session.save(teacher);
				
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
				Teacher teach = (Teacher) session.get(Teacher.class, teacher.getId());
				
				System.out.println(teach);
				
				List<Student> students = session.createFilter(teach.getStudents(), "select this where this.gender='female'").list();
				
				for (Student student:students) {
					System.out.println(student);
				}
				
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
//				// log stack trace
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
