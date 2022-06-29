package com.cjrcodes.ces.services;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjrcodes.ces.models.Contact;
import com.cjrcodes.ces.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private final ContactRepository contactRepository;

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

	public Optional<Contact> getById(long id) {
		try {
			return this.contactRepository.findById(id);
		}

		catch (Exception e) {
			throw new NoSuchElementException("No contact found with id: " + id);
		}
	}

	public void deleteById(long id) {
		try {
			this.contactRepository.deleteById(id);
		} catch (Exception e) {
			throw new NoSuchElementException("No contact found with id: " + id);
		}
	}

	public Contact createContact(Contact contact) {
		return this.contactRepository.save(contact);

	}

	public Contact updateContact(long id, Contact updatedContact) {

		try {


			
			Contact contact = this.contactRepository.getReferenceById(id);
			contact.setAddress(updatedContact.getAddress());
			contact.setEmail(updatedContact.getEmail());
			contact.setName(updatedContact.getName());
			contact.setPhone(updatedContact.getPhone());

			
			return this.contactRepository.save(contact);
			
		}

		catch (Exception e) {
			throw new NoSuchElementException("No contact found with id: " + id);
		}

	}

}
