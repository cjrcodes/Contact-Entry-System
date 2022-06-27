package com.cjrcodes.ces.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private long id;

	@Column(name="STREET")
	private String street;
	
	@Column(name="CITY")
    private String city;
	
	@Column(name="STATE")
    private String state;
	
	@Column(name="ZIP")
    private String zip;
    
	/*
	 * @OneToOne(mappedBy = "address") private Contact contact;
	 */
	
    public Address() {
    	
    }
	
	
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	/*
	 * public Contact getContact() { return contact; }
	 * 
	 * 
	 * public void setContact(Contact contact) { this.contact = contact; }
	 */
    
	
	
    
	
}
