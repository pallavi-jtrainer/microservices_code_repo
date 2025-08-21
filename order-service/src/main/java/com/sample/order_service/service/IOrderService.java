package com.sample.order_service.service;

import java.util.Map;

import com.sample.order_service.dto.OrderDto;

public interface IOrderService {
	OrderDto createOrder(OrderDto order);
	Map<String, Object> getOrders(Long id);
}
