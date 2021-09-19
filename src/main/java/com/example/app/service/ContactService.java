package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.ContactRepository;
import com.example.app.dto.Contact;

@Service
public class ContactService {
    
	@Autowired
	ContactRepository contactRepository;
	
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
	
	public List<Contact> lstAllContact(){
	   return contactRepository.findAll();	
	}
}
