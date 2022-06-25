package com.cjrcodes.ces.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cjrcodes.ces.models.Address;
import com.cjrcodes.ces.repositories.AddressRepository;

@Service
public class AddressService {
	
	private AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public List<Address> getAllAddresss() {
		Iterable<Address> Address = this.addressRepository.findAll();
		List<Address> AddressList = new ArrayList<>();
		Address.forEach(AddressList::add);

		AddressList.sort(new Comparator<Address>() {
			@Override
			public int compare(Address o1, Address o2) {

				return o1.getState().compareTo(o2.getState());
			}
		});

		return AddressList;
	}

	public Address getById(long id) {
		return addressRepository.findById(id).get();
	}

}
