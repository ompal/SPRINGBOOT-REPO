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
import com.example.boot.entity.Users;
import com.example.boot.repository.RoleRepository;

@RestController 
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping("/role")
	public ResponseEntity<?> saveRole(@RequestBody Role role){
		roleRepository.save(role);
		return new ResponseEntity<>("Role saved successfully!",HttpStatus.CREATED);
	}
	
	@GetMapping("/role")
	public ResponseEntity<?> getAllRole(){
		List<Role> listRole = roleRepository.findAll();
		if(listRole.isEmpty()) {
			return new ResponseEntity<>("NO role found!",HttpStatus.NO_CONTENT);
		}
		
		for(Role role : listRole) {
			System.out.println("Role -> "+role.getRole()+" : ");
			for(Users user : role.getUsers()) {
				System.out.println("User -> "+user.getUsername());
			}
		}
		return new ResponseEntity<>(listRole,HttpStatus.OK);
	}
}
