package com.example.demo.service;

import com.example.demo.entities.Order;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface OrderService {
	
	List<Order> getAllOrders();
	
	void deleteOrder(Order order);
	
	List<Order> getOrderByStatus(Enum status);
	
	byte[] getSticker(List<Long> orderIds, String type, int width, int height) throws IOException, WriterException;
}
