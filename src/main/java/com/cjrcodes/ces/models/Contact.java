package com.cjrcodes.ces.models;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_ID")
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NAME_ID", referencedColumnName = "NAME_ID")
	@NonNull
	private Name name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTACT_ID")
	private List<Phone> phone = new ArrayList<>();

	@Column(name = "EMAIL")
	private String email;

	public Contact() {

	}

	public Contact(Name name, Address address, List<Phone> phone, String email) {
		this.name = name;
		this.address = address;
		this.phone = phone;
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

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

	public List<Phone> getPhone() {
		return phone;
	}

	public void setPhoneNumbers(List<Phone> phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
