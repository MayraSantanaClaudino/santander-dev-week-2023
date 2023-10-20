package me.dio.controller;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import me.dio.service.UserService;
import me.dio.domain.model.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService){
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		var user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User userToCreate){
		var userCreated = userService.create(userToCreate);
		URI location = ((UriComponentsBuilder) ServletUriComponentBuilder.fromCurrentRequest())
				.path("/{id}")
				.buildAndExpand(userCreated.getId())
				.toUri();
		return ResponseEntity.created(location).body(userCreated);
	}
}
