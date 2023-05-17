package com.example.pp6project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.pp6project.Entity.Admin;
import com.example.pp6project.Repository.AdminRepository;



@RestController
@ComponentScan(basePackages={"com.example"})
@RequestMapping("/api")
@ConfigurationProperties("spring")
public class AdminController {
	@Autowired
	public AdminRepository a_repo;
	@RequestMapping("/welocome")
	public ModelAndView welcomePage() {
		var admin = new ModelAndView();
	    admin.setViewName("adminlogin");
		 
		return admin;
	}
	@GetMapping("/adminvalidation")
	public ModelAndView adminPage(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap model) {
		List<Admin> admin = a_repo.find(email, password);
		String message = null;

		if (admin.isEmpty()) {
			model.addAttribute("message", "Invalid Admin Credentials");
			var adminl = new ModelAndView();
			adminl.addObject(message,"Invalid Admin Credentials");
		    adminl.setViewName("adminlogin");
			 
			return adminl;		} else {
				var adminl = new ModelAndView();
				String name = null;
				adminl.addObject(message,admin.get(0).getName());
			    adminl.setViewName("adminpage");
			return adminl;

		}

	}
	
}
