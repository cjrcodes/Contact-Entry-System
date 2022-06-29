package com.cjrcodes.ces.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjrcodes.ces.models.Contact;
import com.cjrcodes.ces.models.Phone;
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
		// phoneService.createPhone(null, contact)
		/*
		 * List<Phone> phones = contact.getPhone(); System.out.println(phones.size());
		 * phones.forEach(d -> System.out.println(d.getNumber()));
		 */
		// phones.forEach(p -> p.setContact(contact));
		// phones.forEach(p -> this.phoneService.createPhone(p, contact));
		return this.contactRepository.save(contact);

	}

	// @Transactional
	public Contact updateContact(long id, Contact updatedContact) {

		try {

			/*
			 * Contact updatedContact = this.contactRepository.getReferenceById(id);
			 * 
			 * if(updatedContact == null) { throw new
			 * NoSuchElementException("No contact found with id: " + id);
			 * 
			 * }
			 * 
			 * updatedContact.setcontact(contact.getcontact()); updatedContact.
			 * 
			 * 
			 * 
			 * return
			 */

			/*
			 * this.contactRepository .findById(id) // returns Optional<User> .ifPresent(c
			 * -> { c.setcontact(contact.getcontact()); c.setEmail(null);
			 * 
			 * repository.save(user1); });
			 */

			/*
			 * return this.contactRepository.findById(contact.getId()).map(c -> {
			 * c.setAddress(contact.getAddress()); c.setEmail(contact.getEmail());
			 * c.setName(contact.getName()); c.setPhone(contact.getPhone());
			 * 
			 * // c.getPhone().clear(); return this.contactRepository.save(contact);
			 * 
			 * }).orElseGet(() -> { return this.contactRepository.save(contact); });
			 */

			// Contact updatedContact =
			// this.contactRepository.getReferenceById(contact.getId());
			// return this.contactRepository.save(contact);
			
			Contact contact = this.contactRepository.getReferenceById(id);
			//contact.getPhone().clear();
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
