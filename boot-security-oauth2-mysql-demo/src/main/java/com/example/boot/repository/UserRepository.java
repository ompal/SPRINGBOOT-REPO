package com.example.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot.entity.Users;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users, Long> {
	 
	Optional<Users> findByEmail(String email);
	
	Optional<Users> findByUsername(String username);
}
