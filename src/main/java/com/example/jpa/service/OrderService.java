package com.example.jpa.service;

import java.util.List;

import com.example.jpa.entity.Order;

import jakarta.servlet.http.HttpSession;

public interface OrderService {
	Order placeOrder(String productId, int quantity, HttpSession session);
	List<Order> getMyOrders(HttpSession session);
}
