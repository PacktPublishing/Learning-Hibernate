package com.packt.hibernate.ch2.listing08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class OneToManyMain {

	public static void main(String[] args) {
		
		Room room = new Room();
		room.setLength(8.5);
		room.setWidth(12.4);
		
		RoomId roomId = new RoomId();
		roomId.setBuildingNumber(4);
		roomId.setRoomNumber(5);
		room.setId(roomId);

		Occupant oc1 = new Occupant();
		oc1.setCheckinDate(new Date());
		oc1.setCheckoutDate(new Date());
		
		oc1.setRoomId(roomId);
		
		Occupant oc2 = new Occupant();
		oc2.setCheckinDate(new Date());
		oc2.setCheckoutDate(new Date());
		oc2.setRoomId(roomId);
		
		Collection<Occupant> occupants = new ArrayList<Occupant>();
		occupants.add(oc1);
		occupants.add(oc2);
		
		room.setOccupants(occupants);
		
		try {
			Session session = HibernateUtil
					.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				session.save(room);
				session.save(oc1);
				session.save(oc2);
				
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
		
		try {
			Session session = HibernateUtil
					.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			try {
				Room roomFetched = (Room) session.get(Room.class, roomId);
				Iterator<Occupant> it = roomFetched.getOccupants().iterator();
				Occupant ocr = null;
				while (it.hasNext()) {
					Occupant ocRemoved = it.next();
					if (ocRemoved.getId() == 2) {
						ocr = ocRemoved;
					}
				}
				roomFetched.getOccupants().remove(ocr);

				session.persist(roomFetched);
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
