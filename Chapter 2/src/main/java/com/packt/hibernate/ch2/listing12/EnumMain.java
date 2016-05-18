package com.packt.hibernate.ch2.listing12;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EnumMain {

	public static void main(String[] args) {
		try {

			WorkSchedule sched = new WorkSchedule();
			sched.setStartTime(new Timestamp(
					(new GregorianCalendar(0, 0, 0, 8, 30)).getTimeInMillis()));
			sched.setEndTime(new Timestamp((new GregorianCalendar(0, 0, 0, 17, 30))
					.getTimeInMillis()));
			sched.setWorkDay(WeekDay.Sunday);

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(sched);

				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				// log stack trace
				e.printStackTrace();

			} finally {
				if (session.isOpen())
					session.close();
			}

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			try {
				WorkSchedule sched2 = (WorkSchedule) session.get(
						WorkSchedule.class, sched.getId());
				System.out.println(sched2);
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
