package com.thedevbridge.tpfinalbackend.endpoint;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thedevbridge.tpfinalbackend.dao.ContactDAO;
import com.thedevbridge.tpfinalbackend.domain.Contact;

@RestController
@RequestMapping(value = "api/contact")
public class ContactEndpoint {
	
	@Inject
	private ContactDAO contactDAO;
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody Contact contact) {
		contactDAO.create(contact);
	}
	
	@RequestMapping(value = "/findAll", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Contact> findAll() {
		List<Contact> contacts = contactDAO.findAll();
		return contacts;
	}
}
