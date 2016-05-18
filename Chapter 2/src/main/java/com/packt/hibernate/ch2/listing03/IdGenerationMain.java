package com.packt.hibernate.ch2.listing03;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class IdGenerationMain {

	public static void main(String[] args) {
		try {
			Player player = new Player();
			player.setName("LeBron");
			Player player2 = new Player();
			player2.setName("Jordan");

			Game game = new Game();
			game.setName("Basketball");
			
			Game game2 = new Game();
			game2.setName("Football");
			
			Game game3 = new Game();
			game3.setName("Soccer");
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(player);
				session.save(player2);
				session.save(game);
				session.save(game2);
				session.save(game3);
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
