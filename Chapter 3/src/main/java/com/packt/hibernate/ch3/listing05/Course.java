package com.packt.hibernate.ch3.listing05;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef(name="genderFilter", 
		parameters=@ParamDef(name="gender", type="character"))
public class Course {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Filter (
			name="genderFilter",
			condition="gender = :gender"
	)
	private Set<Student> students = new HashSet<Student>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
