package com.example.pp6project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pp6project.Entity.Admin;
import com.example.pp6project.Repository.AdminRepository;



@RestController
@ComponentScan(basePackages={"com.example.pp6project"})
@RequestMapping("/api")
public class AdminController {
	@Autowired
	public AdminRepository a_repo;
	@RequestMapping("/welocome")
	public String welcomePage() {
		return "adminlogin";
	}
	@GetMapping("/adminvalidation")
	public String adminPage(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap model) {
		List<Admin> admin = a_repo.find(email, password);

		if (admin.isEmpty()) {
			model.addAttribute("message", "Invalid Admin Credentials");
			return "adminlogin";
		} else {
			model.addAttribute("message", admin.get(0).getName());
			return "adminpage";

		}

	}
	
}
