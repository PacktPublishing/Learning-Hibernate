package com.packt.hibernate.ch2.listing04;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class IdGenerationHibernateForeignMain {

	public static void main(String[] args) {
		try {
			Circle circle = new Circle();
			Center center = new Center();
			
			circle.setRadius(5.0f);
			center.setxPos(3.0f);
			center.setyPos(2.0f);
			
			center.setCircle(circle);
			circle.setCenter(center);
			
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(circle);
				session.save(center);

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
