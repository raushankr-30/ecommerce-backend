package com.example.jpa.service;

import java.util.List;

import com.example.jpa.entity.Product;

import jakarta.servlet.http.HttpSession;

public interface ProductService {
	public void insertProd(Product product, HttpSession session);
	public List<Product> getAllProduct();
	Product updateProduct(String id, Product product, HttpSession session);
	void deleteProduct(String id, HttpSession session);
}
