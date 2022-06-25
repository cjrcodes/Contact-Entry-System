package com.cjrcodes.ces.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjrcodes.ces.models.Contact;
import com.cjrcodes.ces.services.ContactService;
import java.util.List;


@Controller
@RequestMapping("/contacts")
public class ContactController {

	private final ContactService contactService;
	
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@GetMapping
	public String getAllContacts(Model model) {
		List<Contact> contacts = this.contactService.getAllContacts();
		model.addAttribute("contacts", contacts);
		
		return "contacts";
	}
	
}
