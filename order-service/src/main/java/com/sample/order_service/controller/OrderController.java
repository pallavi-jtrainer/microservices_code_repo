package com.sample.order_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.order_service.dto.OrderDto;
import com.sample.order_service.service.IOrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private IOrderService service;
	
	@PostMapping
	public OrderDto createOrder(@RequestBody OrderDto order) {
		return service.createOrder(order);
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getDetails(@PathVariable Long id) {
		return service.getOrders(id);
	}
}
