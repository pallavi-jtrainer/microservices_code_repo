package com.sample.order_service.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sample.order_service.dto.OrderDto;
import com.sample.order_service.entity.Order;
import com.sample.order_service.exception.ResourceNotfoundException;
import com.sample.order_service.repository.OrderRepository;
import com.sample.order_service.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private WebClient webClient;

	@Override
	public OrderDto createOrder(OrderDto order) {
		
		Order o = new Order();
		o.setProductId(order.getProductId());
		o.setQuantity(order.getQuantity());
		
		Order savedOrder = repo.save(o);
		
		OrderDto dto = new OrderDto();
		dto.setId(savedOrder.getId());
		dto.setProductId(savedOrder.getProductId());
		dto.setQuantity(savedOrder.getQuantity());
		return dto;
	}

	@Override
	public Map<String, Object> getOrders(Long id) {
		Order order = repo.findById(id).orElseThrow(
				() -> new ResourceNotfoundException("Order not found")
				);
		Map product = webClient.get()
			.uri("/api/products/" + order.getProductId())
			.retrieve()
			.bodyToMono(Map.class)
			.block();
		return Map.of("orderId", order.getId(), "quantity", order.getQuantity(), "product", product);
	}

}
