package com.example.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.entity.Product;
import com.example.jpa.service.ProductService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(
		origins = "http://localhost:5173",
		allowCredentials = "true"
    )
public class ProductController {
	@Autowired
	ProductService pservice;
	@PostMapping("/addprod")
	public String addProduct(@RequestBody Product product, HttpSession session) {
		pservice.insertProd(product, session);
		return "Product added Successfully!";
	}
	@GetMapping("/getprod")
	public List<Product> getAllProd(){
		return pservice.getAllProduct();
	}
	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable String id, @RequestBody Product product, HttpSession session) {
		return pservice.updateProduct(id, product, session);
		
	}
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id, HttpSession session) {
		pservice.deleteProduct(id, session);
		return "Product Deleted Successfully!";
		
	}
}
