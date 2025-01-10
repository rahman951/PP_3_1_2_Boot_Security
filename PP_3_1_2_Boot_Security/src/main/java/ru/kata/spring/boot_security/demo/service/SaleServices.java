package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entities.Product;
import ru.kata.spring.boot_security.demo.entities.Sale;

import java.util.List;

public interface SaleServices {
	List<Sale> findAll();
	
	List<Sale> findByStatus(String status);
	
	List<Sale> findByDate(String date);
	
	List<Sale> findByProduct(Product product);
	
}
