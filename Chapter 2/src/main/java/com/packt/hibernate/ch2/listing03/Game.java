package com.packt.hibernate.ch2.listing03;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Game {

	@Id
	@TableGenerator(name = "TABLE_GENERATOR",
    	table = "ALL_SEQUENCES",
    	pkColumnName = "TABLE_NAME",
    	valueColumnName = "NEXT_ID",
    	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, 
					generator = "TABLE_GENERATOR")
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
