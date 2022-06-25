package com.cjrcodes.ces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjrcodes.ces.models.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
