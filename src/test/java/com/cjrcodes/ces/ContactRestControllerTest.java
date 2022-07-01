package com.cjrcodes.ces;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cjrcodes.ces.controllers.ContactRestController;
import com.cjrcodes.ces.models.Address;
import com.cjrcodes.ces.models.Contact;
import com.cjrcodes.ces.models.Name;
import com.cjrcodes.ces.models.Phone;
import com.cjrcodes.ces.models.PhoneType;
import com.cjrcodes.ces.services.ContactService;
import com.fasterxml.jackson.core.sym.Name1;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.description.modifier.EnumerationState;

@WebMvcTest(controllers = ContactRestController.class)
public class ContactRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private List<Name> names;
	private List<Address> addresses;
	private List<Phone> phones;
	private List<Contact> contacts;

	@MockBean
	ContactService contactService;

	//Dummy values to compare or simply use values directly, not necessarily following a standard or set of practices
	@BeforeEach
	public void setup() {

		Name name1 = new Name("Christian", "", "Rodriguez");
		name1.setId(1);

		Name name2 = new Name("Johnny", "", "Greenwood");
		name2.setId(2);

		Name name3 = new Name("Jodie", "Fostie", "");
		name3.setId(3);

		Name name4 = new Name("Greg", "", "");
		name4.setId(4);

		Name name5 = new Name("Niraj", "", "Patel");
		name5.setId(5);

		names = List.of(name1, name2, name3, name4, name5);

		Address address1 = new Address("8360 High Autumn Row", "Cannon", "Delaware", "19797");
		address1.setId(1);

		Address address2 = new Address("46 Clerk Pl", "Onion", "Delaware", "19797");
		address2.setId(2);

		Address address3 = new Address("170 Hoppe Creek Suite 985", "Balistrerimouth", "Alabama", "40241-5454");
		address3.setId(3);

		Address address4 = new Address("1997 Greenwood Road", "New Agnes", "New Hampshire", "98065");
		address4.setId(4);

		Address address5 = new Address("87046 Schulist Cape", "London", "Ohio", "41232");
		address5.setId(5);

		addresses = List.of(address1, address2, address3, address4, address5);

		Phone phone1 = new Phone("123-932-742-3453", PhoneType.mobile);
		phone1.setId(1);

		Phone phone2 = new Phone("908-809-7321", PhoneType.home);
		phone2.setId(2);

		Phone phone3 = new Phone("772-844-7613", PhoneType.work);
		phone3.setId(3);

		Phone phone4 = new Phone("944-771-8001", PhoneType.home);
		phone4.setId(4);

		Phone phone5 = new Phone("302-532-9427", PhoneType.home);
		phone5.setId(5);

		phones = List.of(phone1, phone2, phone3, phone4, phone5);

		contacts = new ArrayList<>(Arrays.asList(new Contact(names.get(0), addresses.get(0), "cjrcodes@gmail.com"),
				new Contact(names.get(1), addresses.get(1), "radioheadguitarist@protonmail.com"),
				new Contact(names.get(2), addresses.get(2), "manlybord@protonmail.com")));
		
		contacts.get(0).setPhone(List.of(phones.get(0), phones.get(1)));
		contacts.get(1).setPhone(List.of(phones.get(2), phones.get(3)));
		contacts.get(2).setPhone(List.of(phones.get(4)));

		
		contacts.get(0).setId(1);
		contacts.get(1).setId(2);
		contacts.get(2).setId(3);
	}

	@Test
	public void testCreateContact() throws Exception {

		Contact contact = new Contact(new Name("Christian", "", "Rodriguez"), new Address("8360 High Autumn Row", "Cannon", "Delaware", "19797"), "cjrcodes@gmail.com");


		mockMvc.perform(post("/api/contacts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact))).andExpect(status().isCreated()).andDo(print());

	}

	@Test
	public void testGetAllContacts() throws Exception {

		when(contactService.getAllContacts()).thenReturn(contacts);
		mockMvc.perform(get("/api/contacts")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(contacts.size())).andDo(print());
	}

	@Test
	public void testGetContactById() throws Exception {
		
	    long id = 1L;
		Contact contact = new Contact(new Name("Christian", "", "Rodriguez"), new Address("8360 High Autumn Row", "Cannon", "Delaware", "19797"), "cjrcodes@gmail.com");
		contact.setId(id);
		
		when(contactService.getById(id)).thenReturn(Optional.of(contact));
	    mockMvc.perform(get("/api/contacts/{id}", id)).andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(id))
				/* Needs further debugging to match memory addresses
				 * .andExpect(jsonPath("$.address").value(contact.getAddress()))
				 * .andExpect(jsonPath("$.name").value(contact.getName()))
				 * .andExpect(jsonPath("$.email").value(contact.getEmail()))
				 * .andExpect(jsonPath("$.phone").value(contact.getPhone()))
				 */
	        .andDo(print());
	}
	
	 @Test
	  void testUpdateContact() throws Exception {
	    long id = 1L;
		Contact contact = new Contact(new Name("Christian", "", "Rodriguez"), new Address("8360 High Autumn Row", "Cannon", "Delaware", "19797"), "cjrcodes@gmail.com");
		Contact updatedContact = new Contact(new Name("Jane", "", "Additon"), new Address("321 LowHighs Plain", "Lore", "New Jersey", "45676"), "janesaddiction@gmail.com");
		contact.setId(id);
		updatedContact.setId(id);
	    when(contactService.getById(id)).thenReturn(Optional.of(contact));
	    when(contactService.updateContact(id, updatedContact)).thenReturn(updatedContact);
	    mockMvc.perform(put("/api/contacts/{id}", id).contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(updatedContact)))
	        .andExpect(status().isOk())
				/*
				 * .andExpect(jsonPath("$.title").value(updatedContact.getTitle()))
				 * .andExpect(jsonPath("$.description").value(updatedContact.getDescription()))
				 * .andExpect(jsonPath("$.published").value(updatedContact.isPublished()))
				 */
	        .andDo(print());
	  }
	
	  @Test
	  void testDeleteContact() throws Exception {
	    long id = 1L;
	    doNothing().when(contactService).deleteById(id);
	    mockMvc.perform(delete("/api/contacts/{id}", id))
	         .andExpect(status().is2xxSuccessful())
	         .andDo(print());
	  }

}
