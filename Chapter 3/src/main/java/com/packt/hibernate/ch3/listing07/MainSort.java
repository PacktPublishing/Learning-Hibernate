package com.packt.hibernate.ch3.listing07;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainSort {

	public static void main(String[] args) {
		
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				Team team = (Team) session.get(Team.class, new Long(1));
				
				Iterator<Person> persons = team.getPersons().iterator();
				
				while (persons.hasNext()) {
					System.out.println(persons.next());
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
