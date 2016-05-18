package com.packt.hibernate.ch2.listing02;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ValueMain {

	public static void main(String[] args) {
		try {
			
			DailyStockPrice price = new DailyStockPrice();
			DailyStats dailyStats = new DailyStats();
			
			price.setDate(new Date());
			dailyStats.setHigh(20.5);
			dailyStats.setLow(18.95);
			dailyStats.setOpeningPrice(16.65);
			dailyStats.setClosingPrice(20.1);
			dailyStats.setVolume(15002324);
			
			price.setDailyStats(dailyStats);
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(price);
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
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			try {
				DailyStockPrice dailyPrice = (DailyStockPrice) session.get(DailyStockPrice.class, new Long(1));
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
