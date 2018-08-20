package com.example.boot;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.boot.controller.RoleController;
import com.example.boot.entity.Role;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RoleController.class, secure = false)
public class BootSecurityDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	 
	//Role mocRole = new Role(1,"HR");
	
	String roleJson = "{\"id\":\"1\",\"role\":\"HR\"}";

}
