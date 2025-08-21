package com.sample.product_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.product_service.dto.ProductDto;
import com.sample.product_service.entity.Product;
import com.sample.product_service.exception.ResourceNotFoundException;
import com.sample.product_service.repository.ProductRepository;
import com.sample.product_service.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		Product p = productRepository.save(product);
		
		ProductDto dto = new ProductDto();
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setPrice(p.getPrice());
		return dto;
	}

	@Override
	public ProductDto getProductDetails(Long id) {
		Product p = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product with id: " + id + " not found")
				);
		ProductDto dto = new ProductDto();
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setPrice(p.getPrice());
		return dto;
	}

}
