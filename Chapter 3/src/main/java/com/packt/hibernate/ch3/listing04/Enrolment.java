package com.packt.hibernate.ch3.listing04;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Enrolment {
	
	@EmbeddedId
	private EnrolmentId id;	
	private String grade;

	public EnrolmentId getId() {
		synchronized(this) {
			if (id == null) {
				id = new EnrolmentId();
			}
		}
		return id;
	}

	public void setId(EnrolmentId id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return getId().getStudent();
	}

	public void setStudent(Student student) {
		getId().setStudent(student);
	}

	public Course getCourse() {
		return getId().getCourse();
	}

	public void setCourse(Course course) {
		getId().setCourse(course);
	}
}
