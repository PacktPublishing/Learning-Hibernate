package com.packt.hibernate.ch3.listing13;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Subselect("select row_number() over() as id, " 
		+ "   p.firstname, p.lastname, l.name as language, "
		+ "   max(l.year) as lastused, count(*) as numyears "
		+ "from person p join language l " + "on p.id = l.person_id "
		+ "group by p.firstname, p.lastname, l.name")
@Synchronize({ "person", "language" })
public class Experience {
	@Id
	private long id;
	private String firstname;
	private String lastname;
	private String language;
	private long lastUsed;
	private long numYears;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(long lastUsed) {
		this.lastUsed = lastUsed;
	}

	public long getNumYears() {
		return numYears;
	}

	public void setNumYears(long numYears) {
		this.numYears = numYears;
	}

	@Override
	public String toString() {
		return "Experience [id=" + id + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", language=" + language
				+ ", lastUsed=" + lastUsed + ", numYears=" + numYears + "]";
	}

}
