package com.example.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.entity.Role;
import com.example.boot.services.IGenericService;
 
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private IGenericService<Role> iGenericServicRole;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getAllRole() {
		List<Role> listRoles = iGenericServicRole.fetchAll(new Role()," order by id DESC");
		if (listRoles.isEmpty()) {
			return new ResponseEntity<String>("NO Role details found!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(listRoles, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getRoleById(Role role, @PathVariable String id) {
		try {
			role = iGenericServicRole.find(new Role(), " where id = "+Integer.parseInt(id)+"");
			if (role == null) {
				return new ResponseEntity<String>("Invalid Role id " + id + "!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@PostMapping()
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> saveRole(@RequestBody @Valid Role role, Errors error) {
		if (error.hasErrors()) {
			return new ResponseEntity<String>(error.getFieldError().getDefaultMessage().toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			iGenericServicRole.create(role);
			return new ResponseEntity<Role>(role, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping()
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateRole(@RequestBody @Valid Role role, Errors error) {
		if (error.hasErrors()) {
			return new ResponseEntity<String>(error.getFieldError().getDefaultMessage().toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			iGenericServicRole.update(role);
			return new ResponseEntity<Role>(role, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteRole(Role role, @PathVariable String id) {
		role = iGenericServicRole.find(new Role()," where id = "+Integer.parseInt(id)+"");
		if (role == null) {
			return new ResponseEntity<String>("Invalid Role id " + id + "!", HttpStatus.BAD_REQUEST);
		}
		try {
			iGenericServicRole.delete(role);
			return new ResponseEntity<Role>(role, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
