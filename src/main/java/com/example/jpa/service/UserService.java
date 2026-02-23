package com.example.jpa.service;

import com.example.jpa.entity.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	void register(User user);
	User login(String username, String password, HttpSession session);
	void logout(HttpSession session);
}
