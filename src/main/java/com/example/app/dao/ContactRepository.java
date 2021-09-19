package com.example.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.dto.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
