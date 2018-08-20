package com.example.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.entity.Users;
import com.example.boot.servces.IGenericService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IGenericService<Users> userService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping() 
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getUsersDetails(Users user) {
		List<Users> listUsers = userService.fetchAll(user);
		if (listUsers.isEmpty()) {
			return new ResponseEntity<>("NO user details found!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listUsers, HttpStatus.OK);
	}

	@PostMapping() 
	public ResponseEntity<?> saveUser(@RequestBody @Valid Users user,Errors error) {
		if(error.hasErrors()) { 
			return new ResponseEntity<>(error.getFieldError().getDefaultMessage().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); 
	        //user.setRoles(user.getRoles());
			userService.create(user);
			return new ResponseEntity<>("User details saved successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@DeleteMapping() 
	public ResponseEntity<?> deleteUser(@RequestBody Users user) {
		userService.delete(user);
		return new ResponseEntity<>("User deleted successfully!", HttpStatus.GONE);
	}

	@PutMapping() 
	public ResponseEntity<?> updateUser(@RequestBody @Valid Users user) {
		userService.update(user);
		return new ResponseEntity<>("User updated successfully!", HttpStatus.GONE);
	}

}
