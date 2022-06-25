package com.cjrcodes.ces.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CONTACT_ID")
	private long id;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NAME_ID", referencedColumnName = "NAME_ID")
	private Name name;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
	private Address address;
	
	@ElementCollection
    @CollectionTable(name="PHONE")
	private List<Phone> phoneNumbers;
	
	@Column(name = "EMAIL")
	private String email;

	public Contact(int id, Name name, Address address, ArrayList<Phone> phoneNumbers, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumbers = phoneNumbers;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Phone> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(ArrayList<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
