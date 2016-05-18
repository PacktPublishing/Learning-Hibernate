package com.packt.hibernate.ch2.listing04;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Parameter;

@Entity
@org.hibernate.annotations.GenericGenerator(name = "custom-generator", strategy = "foreign", parameters = { @Parameter(name = "property", value = "circle") })
public class Center {

	@Id
	@GeneratedValue(generator = "custom-generator")
	private long id;
	private float xPos;
	private float yPos;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Circle circle;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

}
