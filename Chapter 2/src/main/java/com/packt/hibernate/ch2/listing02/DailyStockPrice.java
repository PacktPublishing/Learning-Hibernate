package com.packt.hibernate.ch2.listing02;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(
		name = "daily_stats",
		pkJoinColumns = {
				@PrimaryKeyJoinColumn(name="id")
		})
public class DailyStockPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date date;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="openingPrice",
				column=@Column(name="openingPrice", table="daily_stats")),
		@AttributeOverride(name="closingPrice",
				column=@Column(name="closingPrice", table="daily_stats")),
		@AttributeOverride(name="high",
				column=@Column(name="high", table="daily_stats")),
		@AttributeOverride(name="low",
				column=@Column(name="low", table="daily_stats")),
		@AttributeOverride(name="volume",
				column=@Column(name="volume", table="daily_stats"))		
	})
	private DailyStats dailyStats;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DailyStats getDailyStats() {
		if (dailyStats == null) {
			return null;
		}
		
		DailyStats copy = new DailyStats();
		copy.setOpeningPrice(dailyStats.getOpeningPrice());
		copy.setClosingPrice(dailyStats.getClosingPrice());
		copy.setHigh(dailyStats.getHigh());
		copy.setLow(dailyStats.getLow());
		copy.setVolume(dailyStats.getVolume());
		
		return copy;
	}

	public void setDailyStats(DailyStats dailyStats) {
		this.dailyStats = dailyStats;
	}
	
}
