package com.example.demo.service;

import com.example.demo.entities.Product;
import com.example.demo.entities.Sale;

import java.util.List;

public interface SaleService {
	List<Sale> findAll();
	
	List<Sale> findByStatus(String status);
	
	List<Sale> findByDate(String date);
	
	List<Sale> findByProduct(Product product);
	
}
