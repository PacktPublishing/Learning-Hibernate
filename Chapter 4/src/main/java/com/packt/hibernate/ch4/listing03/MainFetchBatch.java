package com.packt.hibernate.ch4.listing03;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainFetchBatch {

	public static void main(String[] args) {
		try {

			Owner martha = new Owner();
			martha.setName("Martha");
			
			Owner john = new Owner();
			john.setName("John");
			
			Car toyota = new Car();
			toyota.setModel("Toyota");
	
			Car ford = new Car();
			ford.setModel("Ford");
	
			Car jeep = new Car();
			jeep.setModel("Jeep");
	
			toyota.setOwner(martha);
			ford.setOwner(martha);
			martha.getCars().add(toyota);
			martha.getCars().add(ford);
			
			jeep.setOwner(john);
			john.getCars().add(jeep);
			
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
							
			try {
				session.save(martha);
				session.save(john);
//				session.save(jeep);
//				session.save(ford);
//				session.save(toyota);
				
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
				List<Owner> owners = session.createQuery("from Owner").list();
				for (Owner owner:owners) {
					Set<Car> cars = owner.getCars();
					for(Car car: cars) {
						System.out.println(car);
					}
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
