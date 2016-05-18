package com.packt.hibernate.ch2.listing12;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WorkSchedule {
	@Id
	@GeneratedValue
	private long id;
	
	private Timestamp startTime;
	private Timestamp endTime;

	@Enumerated(EnumType.ORDINAL)
	private WeekDay workDay;

	// getters and setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public WeekDay getWorkDay() {
		return workDay;
	}

	public void setWorkDay(WeekDay workDay) {
		this.workDay = workDay;
	}

	@Override
	public String toString() {
		return "WorkSchedule [id=" + id + ", start=" + startTime + ", end=" + endTime
				+ ", workDay=" + workDay + "]";
	}
}
