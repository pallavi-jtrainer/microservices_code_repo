package com.sample.order_service.exception;

public class ResourceNotfoundException extends RuntimeException {

	public ResourceNotfoundException(String msg) {
		super(msg);
	}
}
