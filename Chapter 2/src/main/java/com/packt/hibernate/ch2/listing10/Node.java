package com.packt.hibernate.ch2.listing10;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Node {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Node parent;
	
	@OneToMany(mappedBy="parent")
	private Collection<Node> children = new ArrayList<Node>();

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

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Collection<Node> getChildren() {
		return children;
	}

	public void setChildren(Collection<Node> children) {
		this.children = children;
	}

	
}
