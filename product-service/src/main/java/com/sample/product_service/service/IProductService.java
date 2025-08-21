package com.sample.product_service.service;

import com.sample.product_service.dto.ProductDto;

public interface IProductService {
	ProductDto createProduct(ProductDto productDto);
	ProductDto getProductDetails(Long id);
}
