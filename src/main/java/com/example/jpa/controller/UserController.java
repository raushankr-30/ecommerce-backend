package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.entity.User;
import com.example.jpa.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(
		origins = "http://localhost:5173",
		allowCredentials = "true"
    )
public class UserController {
	@Autowired
	UserService uservice;
	@PostMapping("/reg")
	public String signUp(@RequestBody User user) {
		uservice.register(user);
		return "Registered Successfully!";
	}
	@PostMapping("/login")
	public User login(@RequestBody User user, HttpSession session) {
		User us=uservice.login(user.getUsername(), user.getPassword(), session);
		return us;
	}
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		uservice.logout(session);
		return "Logout Successful!";
	}
}
