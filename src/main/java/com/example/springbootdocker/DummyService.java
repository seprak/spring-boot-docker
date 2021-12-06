package com.example.springbootdocker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdocker.model.GroceryItem;
import com.example.springbootdocker.repository.ItemRepository;

@RestController
@RequestMapping("/service")
public class DummyService {
	
	@Autowired
    ItemRepository groceryItemRepo;
	
	class User {
		
		public User(String name, String surname) {
			this.name = name;
			this.surname = surname;
		}
		
		public String getName() {
			return name;
		}
		
		public String getSurname() {
			return surname;
		}
		
		String name;
		String surname;
	}

	@RequestMapping("/hello")
	@GetMapping(produces = "application/json")
	private List<GroceryItem> hello() {
		return groceryItemRepo.findAll();
	}
	
}
