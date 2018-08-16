package com.example.boot.servces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.boot.entity.Users;
import com.example.boot.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	 
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Optional<Users> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(Users user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); 
        user.setRoles(user.getRoles());
		userRepository.save(user);
	}

	@Override
	public Optional<Users> findByUsername(String username) { 
		return userRepository.findByUsername(username);
	}

	@Override
	public List<Users> findAllUsers() { 
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Users user) {
		userRepository.delete(user); 
	}

}