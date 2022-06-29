package com.cjrcodes.ces.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjrcodes.ces.models.Phone;
import com.cjrcodes.ces.services.PhoneService;

@RestController
@RequestMapping("/api/phones")

public class PhoneRestController {
	
	@Autowired
	private final PhoneService phoneService;

	public PhoneRestController(PhoneService phoneService) {
		this.phoneService = phoneService;
	}
	
	@GetMapping
    public List<Phone> getAllPhones() {

        return this.phoneService.getAllPhones();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getPhoneById(@PathVariable Long id){
		Optional<Phone> phone = Optional.of(this.phoneService.getById(id));
		if(phone.isPresent()){
		    return ResponseEntity.ok(phone.get());
		}else {
		    return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body("(Get) No phone exists with ID: " + id);
		}

}
}
