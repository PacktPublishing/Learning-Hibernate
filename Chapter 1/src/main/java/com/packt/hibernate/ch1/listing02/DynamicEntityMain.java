package com.packt.hibernate.ch1.listing02;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DynamicEntityMain {

	public static void main(String[] args) {
		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("firstname", "John");
		myMap.put("lastname", "Smith");
		myMap.put("address", "address 123");
		myMap.put("phone", "202-555-1212");
		
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save("DynamicEntity", myMap);
			transaction.commit();
		}
		catch (Exception e) {
			transaction.rollback();			
			e.printStackTrace();
		}
		finally {
			if (session.isOpen())
				session.close();
		}

		HibernateUtil.stopService();			

	}

}
