package com.example.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers("/index/**").permitAll()        
        .antMatchers("/user/**").hasAnyRole("HR")
        .and()
        .httpBasic();		
				 
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .inMemoryAuthentication()
        .withUser("abc")
          .password("xyz")
          .roles("USER")
          .and()
          .withUser("aaa")
          .password("bbb")
          .roles("HR")
          .and()
        .withUser("admin")
          .password("admin")
          .roles("ADMIN");
	}
}