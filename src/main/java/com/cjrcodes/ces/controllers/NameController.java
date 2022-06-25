package com.cjrcodes.ces.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjrcodes.ces.models.Name;
import com.cjrcodes.ces.services.NameService;


@Controller
@RequestMapping("/names")
public class NameController {
	

		private final NameService nameService;
		
		public NameController(NameService nameService) {
			this.nameService = nameService;
		}
		
		@GetMapping
		public String getAllNames(Model model) {
			List<Name> names = this.nameService.getAllNames();
			model.addAttribute("names", names);
			
			return "names";
		}
		
	}
