package com.packt.hibernate.ch1.listing03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BatchMain {

	public void saveStudents(List<Map<String, String>> students) {
		final int batchSize = 2;
		Session session = BatchHibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			int i = 0;
			for (Map<String, String> studentMap:students) {
				i++;
				Student student = new Student();
				student.setFirstname(studentMap.get("firstname"));
				student.setLastname(studentMap.get("lastname"));				
				session.save(student);
				
				if (i % batchSize == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		}
		catch (Exception e) {
			transaction.rollback();
			// log stack trace
		}
		finally {
			if (session.isOpen())
				session.close();
		}		
	}
	
	public static void main(String[] args) {
		BatchMain main = new BatchMain();
		try {
			List<Map<String, String>> studentList = new ArrayList<Map<String, String>>();
			Map<String, String> student = new HashMap<String, String>();
			
			student.put("firstname", "John");
			student.put("lastname", "Smith");
			studentList.add(student);
			
			student = new HashMap<String, String>();
			student.put("firstname", "Sue");
			student.put("lastname", "Martin");
			studentList.add(student);

			student = new HashMap<String, String>();
			student.put("firstname", "Kevin");
			student.put("lastname", "Johnson");
			studentList.add(student);

			student = new HashMap<String, String>();
			student.put("firstname", "Joe");
			student.put("lastname", "Mcneil");
			studentList.add(student);

			
			main.saveStudents(studentList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		BatchHibernateUtil.stopService();			

	}

}
