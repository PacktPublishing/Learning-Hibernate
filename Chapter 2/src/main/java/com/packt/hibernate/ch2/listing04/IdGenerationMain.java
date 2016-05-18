package com.packt.hibernate.ch2.listing04;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class IdGenerationMain {

	public static void main(String[] args) {
		try {
			
			Car car = new Car();
			car.setModel("toyota");
			
			Engine engine = new Engine();
			engine.setSize(1500);
			engine.setCar(car);

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(car);
				session.save(engine);
				
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
