package com.example.app.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.Contact;
import com.example.app.service.ContactService;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("v1")
public class ContacApi {
	private final Logger log =LoggerFactory.getLogger(getClass());
	
	@Autowired
	ContactService contactService;

	@RequestMapping(value="/" , method=RequestMethod.GET)
	@ApiOperation(value = "health", response = Contact.class)
	public String health() {
	  	log.info("Ingresando al metodo de health");
        return "Saludos";
	}

	@RequestMapping(value="/lstcontact" , method=RequestMethod.GET)
	@ApiOperation(value = "Listado del contacto ", response = Contact.class)
	public List<Contact> getContactAll() {
	  	log.info("Ingresando al metodo de listado");
        List<Contact> lstContact=contactService.lstAllContact();
        return lstContact;
	}
	
	@RequestMapping(value = "/Savecontact", method = RequestMethod.POST)
	@ApiOperation(value = "Guardado o actualizado del contacto ", response = Contact.class)
	public Contact updateOrSave(@RequestBody Contact contact) {
	    log.info("Ingresando al metodo de guardado");
	    Contact updateContact=contactService.save(contact);
	    return updateContact;
	}
}
