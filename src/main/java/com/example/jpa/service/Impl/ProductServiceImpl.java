package com.example.jpa.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa.entity.Product;
import com.example.jpa.repository.ProductRepository;
import com.example.jpa.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository prepo;

	private void checkAdmin(HttpSession session) {
		String role=(String) session.getAttribute("role");
		if(role==null || !role.equals("admin")) {
			throw new RuntimeException("Admin access only");
		}
	}
	public void insertProd(Product product, HttpSession session) {
		// TODO Auto-generated method stub
		checkAdmin(session);
		prepo.save(product);
	}

	
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}
	
	public Product updateProduct(String id, Product product, HttpSession session) {
        checkAdmin(session);

        Product existing = prepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());

        return prepo.save(existing);
    }
	
	   
	    public void deleteProduct(String id, HttpSession session) {
	    	checkAdmin(session);
	    	prepo.deleteById(id);
	    }
	
	

}
