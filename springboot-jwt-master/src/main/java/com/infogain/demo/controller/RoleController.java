package com.infogain.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.demo.domain.Role;
import com.infogain.demo.service.IGenericService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private IGenericService<Role> iGenericServiceRole;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping()
	public ResponseEntity<?> getRoles() {
		List<Role> listRole = iGenericServiceRole.fetchAll(new Role());
		if (listRole.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listRole, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> saveRole(@RequestBody Role objRole) {
		
		try { 
			iGenericServiceRole.create(objRole);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping()
	public ResponseEntity<?> updateRole(@RequestBody Role objRole) {
		try {
			iGenericServiceRole.update(objRole);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteRole(@RequestBody Role objRole) {
		iGenericServiceRole.delete(iGenericServiceRole.find(new Role(), objRole.getId()));
		return new ResponseEntity<>(HttpStatus.GONE);
	}

}
