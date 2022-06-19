package com.in28minutes.rest.webservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResouce {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = this.service.findOne(id);
		
		if (user == null)
		{
			throw new UserNotFoundException("id-" + id);
		}else
		{
			return user;
		}
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user)
	{
		User savedUser =this.service.save(user);
		
		URI location = 	ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(savedUser.getId()).toUri();
		
		List<User> allUsers = this.service.findAll();
		
		
		
		for (int i = 0; i<allUsers.size(); i++)
		{
			System.out.println(allUsers.get(i));
		}
		
		return ResponseEntity.created(location).build();
		
		
		
	}
}
