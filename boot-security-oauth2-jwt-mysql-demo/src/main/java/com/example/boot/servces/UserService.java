package com.example.boot.servces;

import java.util.List;
import java.util.Optional;

import com.example.boot.entity.Users;

public interface UserService {
	
	Optional<Users> findUserByEmail(String email);
	
	Optional<Users> findByUsername(String username);

	void saveUser(Users user);
	
	void deleteUser(Users user);
	
	List<Users> findAllUsers();
}