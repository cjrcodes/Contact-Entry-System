package com.cjrcodes.ces.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cjrcodes.ces.models.Contact;
import com.cjrcodes.ces.services.ContactService;

import jakarta.persistence.EntityNotFoundException;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/contacts")

public class ContactRestController {

	private final ContactService contactService;
	
	public ContactRestController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@GetMapping
    public List<Contact> getAllContacts() {

        return this.contactService.getAllContacts();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getContactById(@PathVariable Long id){
		Optional<Contact> contact = this.contactService.getById(id);
		if(contact.isPresent()){
		    return ResponseEntity.ok(contact.get());
		}else {
		    return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body("(Get) No contact exists with ID: " + id);
		}
		  
		
		
    }
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<String> deleteContactById(@PathVariable Long id){
		
		try {
		       this.contactService.deleteById(id);
			    return new ResponseEntity<String>("Sucessfully deleted contact ID: " + id, HttpStatus.OK);

		}
		
		catch (Exception e) {
		    return new ResponseEntity<String>("(Delete) No contact exists with ID: " + id, HttpStatus.NOT_FOUND);
		}
	 			    
    }
	
	@PostMapping
	public ResponseEntity<Contact> createContact(@Validated @RequestBody Contact contact) throws ServerException {
		
		try {
			if(contact == null) {
				throw new ServerException("Contact not valid");
			}

			Contact newContact = this.contactService.createContact(contact);

			
			return new ResponseEntity<Contact>(newContact, HttpStatus.CREATED);
		}
		
		catch (Exception e) {
		    return new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);

		}
		
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateContact(@Validated @RequestBody Contact newContact, @PathVariable long id) throws ServerException {
		
		Optional<Contact> contact = this.contactService.getById(id);
		if(contact.isPresent()){
			
			
			/*
			 * contact.get().setAddress(newContact.getAddress());
			 * contact.get().setEmail(newContact.getEmail());
			 * contact.get().setName(newContact.getName());
			 * contact.get().setPhone(newContact.getPhone());
			 */
			 //newContact.setId(contact.get().getId());
			newContact.setId(id);
			this.contactService.updateContact(id, newContact);
		    return ResponseEntity.ok(newContact);
		}else {
		    return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body("(Get) No contact exists with ID: " + id);
		}
		
		/*
		 * try { if(contact == null) { throw new ServerException("Contact not valid"); }
		 * 
		 * Contact newContact = this.contactService.createContact(contact);
		 * 
		 * 
		 * return new ResponseEntity<Contact>(newContact, HttpStatus.CREATED); }
		 * 
		 * catch (Exception e) { return new
		 * ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
		 * 
		 * }
		 */
		
		
		
	}

	
	
}
