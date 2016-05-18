package com.packt.hibernate.ch3.listing03;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Enrolment {
	
	@EmbeddedId
	private EnrolmentId id;	
	private String grade;

	@MapsId("id")
	@OneToOne
	@JoinColumn(name="studentId")
	private Student student;

	@MapsId("id")
	@OneToOne
	@JoinColumn(name="courseId")
	private Course course;

	public EnrolmentId getId() {
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
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
