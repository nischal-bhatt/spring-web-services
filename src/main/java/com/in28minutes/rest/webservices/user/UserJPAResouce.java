package com.in28minutes.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResouce {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserDaoService service;

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent())
		{
			throw new UserNotFoundException("id-" + id);
		}else
		{
			EntityModel<User> model = EntityModel.of(user.get());
			
			WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
			model.add(linkToUsers.withRel("all-users"));
			return model;
		}
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User savedUser =this.userRepository.save(user);
		
		URI location = 	ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(savedUser.getId()).toUri();
		
		List<User> allUsers = this.service.findAll();
		
		
		
		for (int i = 0; i<allUsers.size(); i++)
		{
			System.out.println(allUsers.get(i));
		}
		
		return ResponseEntity.created(location).build();
		
		
		
	}
	
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		this.userRepository.deleteById(id);
		
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<User> user= userRepository.findById(id);
	    if (!user.isPresent())
	    {
	    	throw new UserNotFoundException("id-" + id);
	    }
	    
	    return user.get().getPosts();
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post)
	{
		Optional<User> user= userRepository.findById(id);
	    if (!user.isPresent())
	    {
	    	throw new UserNotFoundException("id-" + id);
	    }
		
	    User user1 = user.get();
	    
	    post.setUser(user1);
	    
	    postRepo.save(post);
	    
		URI location = 	ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(post.getId()).toUri();
		
		List<User> allUsers = this.service.findAll();
		
		
		
		for (int i = 0; i<allUsers.size(); i++)
		{
			System.out.println(allUsers.get(i));
		}
		
		return ResponseEntity.created(location).build();
		
		
		
	}
	
	
	
	
	
}
