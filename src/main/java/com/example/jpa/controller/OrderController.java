package com.example.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.entity.Order;
import com.example.jpa.service.OrderService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(
		origins = "http://localhost:5173",
		allowCredentials = "true"
    )
public class OrderController {
	@Autowired
	OrderService odservice;
	@PostMapping("/addorder")
	public Order placeOrder(@RequestParam("prodId") String productId, @RequestParam("qty") String quantity, HttpSession session) {
		int qty=Integer.parseInt(quantity);
		return odservice.placeOrder(productId, qty, session);
	}
	@GetMapping("/vieworder")
	public List<Order> myOrders(HttpSession session){
		return odservice.getMyOrders(session);
	}
	
}
