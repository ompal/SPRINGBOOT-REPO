package com.example.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.entity.Role;
import com.example.boot.servces.IGenericService;

@RestController 
public class RoleController {

	@Autowired
	private IGenericService<Role> iGenericServiceRole;
	
	@PostMapping("/role")
	public ResponseEntity<?> saveRole(@RequestBody Role role){
		iGenericServiceRole.create(role);
		return new ResponseEntity<>("Role saved successfully!",HttpStatus.CREATED);
	}
	
	@GetMapping("/role")
	public ResponseEntity<?> getAllRole(Role objRole){
		List<Role> listRole = iGenericServiceRole.fetchAll(objRole);
		if(listRole.isEmpty()) {
			return new ResponseEntity<>("NO role found!",HttpStatus.NO_CONTENT);
		}
		
		listRole.parallelStream().forEach(role -> role.getUsers().forEach(user -> user.getUsername()));
		 
		return new ResponseEntity<>(listRole,HttpStatus.OK);
	}
}
