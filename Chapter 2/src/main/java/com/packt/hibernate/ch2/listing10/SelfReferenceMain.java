package com.packt.hibernate.ch2.listing10;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SelfReferenceMain {

	public static void main(String[] args) {
		Node parent = new Node();
		parent.setName("parent1");
		
		Node child1 = new Node();
		child1.setName("child1");
		child1.setParent(parent);
		
		parent.getChildren().add(child1);

		Node child2 = new Node();
		child2.setName("child2");
		child2.setParent(parent);
		
		parent.getChildren().add(child2);
 		
		try {
			Session session = HibernateUtil
					.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(parent);
				session.save(child1);
				session.save(child2);
				
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
