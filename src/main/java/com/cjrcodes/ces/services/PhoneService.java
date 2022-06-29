package com.cjrcodes.ces.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.cjrcodes.ces.models.Contact;
import com.cjrcodes.ces.models.Phone;
import com.cjrcodes.ces.repositories.PhoneRepository;

@Service
public class PhoneService {

	
	private PhoneRepository phoneRepository;

	public PhoneService(PhoneRepository PhoneRepository) {
		this.phoneRepository = PhoneRepository;
	}

	public List<Phone> getAllPhones() {
		Iterable<Phone> Phone = this.phoneRepository.findAll();
		List<Phone> PhoneList = new ArrayList<>();
		Phone.forEach(PhoneList::add);

		PhoneList.sort(new Comparator<Phone>() {
			@Override
			public int compare(Phone o1, Phone o2) {

				return o1.getType().compareTo(o2.getType());
			}
		});

		return PhoneList;
	}

	public Phone getById(long id) {
		return phoneRepository.findById(id).get();
	}
	
	public void deleteById(long id) {
		try {
			this.phoneRepository.deleteById(id);
		} catch (Exception e) {
			throw new NoSuchElementException("No phone found with id: " + id);
		}
	}
	
	public Phone createPhone(Phone phone, Contact contact) {
		
		phone.setContact(contact);
		System.out.println("Phone Created");
		return this.phoneRepository.save(phone);
	}
	
	
}
