package com.cjrcodes.ces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjrcodes.ces.models.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
