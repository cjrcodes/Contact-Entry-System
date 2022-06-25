package com.cjrcodes.ces.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cjrcodes.ces.models.Name;
import com.cjrcodes.ces.repositories.NameRepository;

@Service
public class NameService {

	private NameRepository nameRepository;

	public NameService(NameRepository nameRepository) {
		this.nameRepository = nameRepository;
	}

	public List<Name> getAllNames() {
		Iterable<Name> Name = this.nameRepository.findAll();
		List<Name> NameList = new ArrayList<>();
		Name.forEach(NameList::add);

		NameList.sort(new Comparator<Name>() {
			@Override
			public int compare(Name o1, Name o2) {

				return o1.getLast().compareTo(o2.getLast());
			}
		});

		return NameList;
	}

	public Name getById(long id) {
		return nameRepository.findById(id).get();
	}
	
}
