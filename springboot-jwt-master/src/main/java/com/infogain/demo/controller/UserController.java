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

import com.infogain.demo.domain.User;
import com.infogain.demo.service.IGenericService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IGenericService<User> iGenericServiceUser;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping()
	public ResponseEntity<?> getUsers() {
		List<User> listUser = iGenericServiceUser.fetchAll(new User());
		if (listUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listUser, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> saveUser(@RequestBody User objUser) {
		
		try {
			objUser.setPassword(bCryptPasswordEncoder.encode(objUser.getPassword()));
			iGenericServiceUser.create(objUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping()
	public ResponseEntity<?> updateUser(@RequestBody User objUser) {
		try {
			iGenericServiceUser.update(objUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteUser(@RequestBody User objUser) {
		iGenericServiceUser.delete(iGenericServiceUser.find(new User(), objUser.getId()));
		return new ResponseEntity<>(HttpStatus.GONE);
	}

}
