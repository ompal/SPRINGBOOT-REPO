package com.example.boot.servces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.boot.entity.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService{
 
    @Autowired
    IGenericService<Users> userService;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.find(new Users(), " where username = '"+username+"'");
        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }
}