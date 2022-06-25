package com.cjrcodes.ces.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NAME")
public class Name {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "NAME_ID")
	private long id;
	
	@Column(name= "FIRST")
	private String first;
	
	@Column(name= "MIDDLE")
	private String middle;
	
	@Column(name= "LAST")
	private String last;
	
	public Name(String first, String middle, String last) {
		this.first = first;
		this.middle = middle;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
	
	
}
