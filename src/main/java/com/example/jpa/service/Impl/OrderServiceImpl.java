package com.example.jpa.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa.entity.Order;
import com.example.jpa.entity.Product;
import com.example.jpa.entity.User;
import com.example.jpa.repository.OrderRepository;
import com.example.jpa.repository.ProductRepository;
import com.example.jpa.repository.UserRepository;
import com.example.jpa.service.OrderService;

import jakarta.servlet.http.HttpSession;
@Service

public class OrderServiceImpl implements OrderService {
	    @Autowired
	    private OrderRepository orderRepo;

	    @Autowired
	    private UserRepository userRepo;

	    @Autowired
	    private ProductRepository productRepo;
	
	public Order placeOrder(String productId, int quantity, HttpSession session) {
		 Long userId = (Long) session.getAttribute("userId");
	        String role = (String) session.getAttribute("role");

	        if (userId == null) {
	            throw new RuntimeException("Please login first");
	        }

	        if (!"user".equals(role)) {
	            throw new RuntimeException("Only users can place orders");
	        }

	        User user = userRepo.findById(userId).orElseThrow();
	        //Product product = productRepo.findById(productId).orElseThrow();
	        Product product=productRepo.findById(productId).orElseThrow();
	        Order order = new Order();
	        order.setUser(user);
	        order.setProduct(product);
	        order.setQuantity(quantity);
	        order.setOrderDate(LocalDateTime.now());

	        return orderRepo.save(order);
	}

	
	public List<Order> getMyOrders(HttpSession session) {
		// TODO Auto-generated method stub
		Long userId = (Long) session.getAttribute("userId");
		if (userId==null) {
			throw new RuntimeException("Login required"); 
		}
		return orderRepo.findAll();
		
	}

}