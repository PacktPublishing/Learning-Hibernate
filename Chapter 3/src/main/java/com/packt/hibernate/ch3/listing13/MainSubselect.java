package com.packt.hibernate.ch3.listing13;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainSubselect {

	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
	            List<Experience> resume = session.createQuery("from Experience").list();  
	            for (Experience exp:resume) {
	            	System.out.println(exp);
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
