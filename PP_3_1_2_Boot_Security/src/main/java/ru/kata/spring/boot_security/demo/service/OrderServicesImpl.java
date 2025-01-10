package com.example.demo.service;

import com.example.demo.entities.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.utils.PngToPdfConverter;
import com.google.zxing.WriterException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepository;
	private PngToPdfConverter pngToPdfConverter;
	
	public OrderServiceImpl(OrderRepository orderRepository, PngToPdfConverter pngToPdfConverter) {
		this.orderRepository = orderRepository;
		this.pngToPdfConverter = pngToPdfConverter;
	}
	
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	@Override
	@Transactional
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}
	
	@Override
	public List<Order> getOrderByStatus(Enum status) {
		List<Order> orders = orderRepository.findAll();
		List<Order> result = new ArrayList<>();
		
		for (Order order : orders) {
			if (order.getStatus().equals(status)) {
				result.add(order);
			}
		}
		
		return result;
	}
	
	@Override
	public byte[] getSticker(List<Long> orderIds, String type, int width, int height) throws IOException, WriterException {
		
		return null;
	}
}
