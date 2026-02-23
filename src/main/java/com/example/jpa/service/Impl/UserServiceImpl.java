package com.example.jpa.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa.entity.User;
import com.example.jpa.repository.UserRepository;
import com.example.jpa.service.UserService;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
	UserRepository urepo;
	public void register(User user) {
		// TODO Auto-generated method stub
		urepo.save(user);

	}

	
	public User login(String username, String password, HttpSession session) {
		// TODO Auto-generated method stub
		User user = urepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("username", user.getUsername());

        return user;
		
	}

	
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();

	}

}
