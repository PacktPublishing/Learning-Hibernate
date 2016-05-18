package com.packt.hibernate.ch2.listing06;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class IdGenerationMain {

	public static void main(String[] args) {
		try {
			Room room = new Room();
			room.setLength(8.5);
			room.setWidth(12.4);
			
			RoomId roomId = new RoomId();
			roomId.setBuildingNumber(4);
			roomId.setRoomNumber(5);
			room.setId(roomId);
			
			Occupant occupant = new Occupant();
			occupant.setCheckinDate(new Date());
			occupant.setCheckoutDate(new Date());
			occupant.setRoomId(roomId);
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(occupant);
				session.save(room);
				
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
