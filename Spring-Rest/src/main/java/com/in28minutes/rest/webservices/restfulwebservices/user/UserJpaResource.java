package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.repo.UserRepository;
import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

	private UserDaoService service;

	private UserRepository repository;

	public UserJpaResource(UserDaoService service,UserRepository repository) {
		this.service = service;
		this.repository=repository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user=repository.findById(id);

		if(user==null)
			throw new UserNotFoundException("id:"+id);
		WebMvcLinkBuilder linkBuilder= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		EntityModel<User> entityModel=EntityModel.of(user.get());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	
}
