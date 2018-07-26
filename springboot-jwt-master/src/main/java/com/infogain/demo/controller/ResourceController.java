package com.infogain.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.demo.domain.RandomCity;
import com.infogain.demo.domain.User;
import com.infogain.demo.service.IGenericService;
 
@RestController 
public class ResourceController { 
    
    @Autowired
	private IGenericService<User> iGenericServiceUser;
    
    @Autowired
	private IGenericService<RandomCity> iGenericServiceRandomCity;

    
    @RequestMapping(value ="/test") 
    public String test(){
        return "Hello world!";
    }
    
    
    @RequestMapping(value ="/springjwt/cities")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<RandomCity> getAllRandomCity(){
        return iGenericServiceRandomCity.fetchAll(new RandomCity());
    }

    @RequestMapping(value ="/springjwt/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return iGenericServiceUser.fetchAll(new User());
    }
}
