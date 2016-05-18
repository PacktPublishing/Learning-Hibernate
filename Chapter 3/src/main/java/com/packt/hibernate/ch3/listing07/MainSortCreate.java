package com.packt.hibernate.ch3.listing07;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainSortCreate {

	public static void main(String[] args) {
		
		Team team = new Team();
		
		Person person = new Person();
		person.setFirstname("David");
		person.setLastname("Smith");
		team.getPersons().add(person);
		
		person = new Person();
		person.setFirstname("Adam");
		person.setLastname("Smith");
		team.getPersons().add(person);

		person = new Person();
		person.setFirstname("David");
		person.setLastname("Brown");
		team.getPersons().add(person);
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				session.save(team);
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
