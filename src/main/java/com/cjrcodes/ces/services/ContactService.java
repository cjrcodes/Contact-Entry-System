package com.cjrcodes.ces.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cjrcodes.ces.models.Contact;
import com.cjrcodes.ces.repositories.ContactRepository;

@Service
public class ContactService {

	private ContactRepository contactRepository;

	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public List<Contact> getAllContacts() {
		Iterable<Contact> Contact = this.contactRepository.findAll();
		List<Contact> contactList = new ArrayList<>();
		Contact.forEach(contactList::add);

		contactList.sort(new Comparator<Contact>() {
			@Override
			public int compare(Contact o1, Contact o2) {

				return o1.getName().getFirst().compareTo(o2.getName().getFirst());
			}
		});

		return contactList;
	}

	public Contact getById(long id) {
		return contactRepository.findById(id).get();
	}
}
